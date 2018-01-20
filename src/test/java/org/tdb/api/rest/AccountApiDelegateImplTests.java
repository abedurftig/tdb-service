package org.tdb.api.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tdb.model.*;
import org.tdb.service.AccountService;
import org.tdb.service.AccountServiceException;
import org.tdb.service.ProjectService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class AccountApiDelegateImplTests {

    private final String accountName = "New Account";
    private final String email = "email@newaccount.com";
    private final String password = "1234";
    private final Long accountId = 1L;

    private final String existingAccountName = "Existing";
    private final String existingEmail = "exists_already@badluck.com";
    private final String projectName = "Test Project";

    private final ProjectDTO projectDTO = new ProjectDTO().name(projectName);
    private final UserDTO userDTO = new UserDTO().email(email).accountId(accountId);
    private final AccountDTO accountDTO = new AccountDTO().name(accountName).owner(userDTO).id(accountId);

    private AccountApiDelegate accountApiDelegate = null;


    @Before
    public void setUp() throws AccountServiceException {

        AccountService accountService = mock(AccountService.class);
        ProjectService projectService = mock(ProjectService.class);

        when(accountService.createAccountAndUser(accountName, email, password)).thenReturn(accountDTO);
        when(accountService.createAccountAndUser(existingAccountName, email, password))
                .thenThrow(new AccountServiceException(AccountServiceException.ErrorCode.ACCOUNT_NAME_TAKEN));
        when(accountService.createAccountAndUser(accountName, existingEmail, password))
                .thenThrow(new AccountServiceException(AccountServiceException.ErrorCode.EMAIL_IS_TAKEN));

        when(accountService.getAccountById(accountId)).thenReturn(ModelMapperImpl.getAccount(accountDTO));
        when(accountService.getAccountDTOById(accountId)).thenReturn(accountDTO);

        when(projectService.createProject(any(), eq(projectName))).thenReturn(projectDTO);

        accountApiDelegate = new AccountApiDelegateImpl(accountService, projectService);

    }

    @Test
    public void createAccount() throws Exception {

        AccountInformation accountInformation =
                new AccountInformation().accountName(accountName).email(email).password(password);

        ResponseEntity<AccountDTO> responseEntity = accountApiDelegate.createAccount(accountInformation);
        assertEquals("status", HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("accountName", accountName, responseEntity.getBody().getName());
        assertEquals("email", email, responseEntity.getBody().getOwner().getEmail());

    }

    @Test
    public void createAccountEmailTaken() throws Exception {

        AccountInformation accountInformation =
                new AccountInformation().accountName(accountName).email(existingEmail).password(password);

        ResponseEntity responseEntity = accountApiDelegate.createAccount(accountInformation);
        assertEquals("status", HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("error message", "This email address is already accociate with a different account.", ((ErrorDTO) responseEntity.getBody()).getMessage() );

    }

    @Test
    public void createAccountNameTaken() throws Exception {

        AccountInformation accountInformation =
                new AccountInformation().accountName(existingAccountName).email(email).password(password);

        ResponseEntity responseEntity = accountApiDelegate.createAccount(accountInformation);
        assertEquals("status", HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("error message", "An account with this name already exists.", ((ErrorDTO) responseEntity.getBody()).getMessage() );

    }

    @Test
    public void failTest() {
        fail("On purpose");
    }
}
