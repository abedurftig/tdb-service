package org.tdb.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.tdb.Application;
import org.tdb.config.WebSecurityConfig;
import org.tdb.model.*;
import org.tdb.test.TestHelper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, WebSecurityConfig.class })
@WebAppConfiguration
public class AccountServiceImplTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void verifyGetAccountById() throws AccountServiceException {

        AccountRepository accountRepository = getMockedAccountRepository(1L, "Test Account", true);
        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, mock(UserRepository.class),
                        TestHelper.getMockedAccountSecurity());

        Account account = accountService.getAccountById(1L);

        Assert.assertThat(account.getId(), Matchers.is(1L));
        Assert.assertThat(account.getName(), Matchers.is("Test Account"));

    }

    @Test
    public void createAccountAndUserWithExistingEmail() {

        AccountRepository accountRepository = getMockedAccountRepository(1L, "Test Account", false);
        UserRepository userRepository = getMockedUserRepository(1L, "john@email.com",  true, "John");

        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, userRepository,
                TestHelper.getMockedAccountSecurity());

        try {
            accountService.createAccountAndUser("Test Account", "john@email.com", "1234");
            Assert.fail("Should throw and exception because a user with the same email already exists.");
        } catch (AccountServiceException e) {
            Assert.assertThat(e.getErrorCode(), Matchers.is(ServiceErrorCode.EMAIL_TAKEN));
        }

    }

    @Test
    public void createAccountAndUserWithExistingAccountName() {

        AccountRepository accountRepository = getMockedAccountRepository(1L, "Test Account", true);
        UserRepository userRepository = getMockedUserRepository(1L, "john@email.com",  true, "John");

        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, userRepository,
                TestHelper.getMockedAccountSecurity());

        try {
            accountService.createAccountAndUser("Test Account", "john@email.com", "1234");
            Assert.fail("Should throw and exception because an account with the same name already exists.");
        } catch (AccountServiceException e) {
            Assert.assertThat(e.getErrorCode(), Matchers.is(ServiceErrorCode.NAME_TAKEN));
        }

    }

    @Test
    public void createAccountAndUserPasswordHashed() {

        AccountRepository accountRepository = getMockedAccountRepository(1L, "Test Account", false);
        UserRepository userRepository = getMockedUserRepository(1L, "john@email.com",  false, "John");

        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, userRepository,
                TestHelper.getMockedAccountSecurity(), passwordEncoder);

        try {

            Account account = accountService.createAccountAndUserInternal("Test Account", "john@email.com", "1234");
            Assert.assertTrue("password should be hashed", passwordEncoder.matches("1234", account.getOwner().getPassword()));

        } catch (AccountServiceException e) {
            e.printStackTrace();
        }

    }

    private static AccountRepository getMockedAccountRepository(Long id, String name, boolean nameExists) {

        AccountRepository accountRepositoryMock = mock(AccountRepository.class);

        Account fakeAccount = TestHelper.fakeAccount(id, name);
        Optional<Account> fakeAccountOptional = nameExists ? Optional.of(fakeAccount) : Optional.empty();

        when(accountRepositoryMock.save(any(Account.class))).thenAnswer(AdditionalAnswers.<String>returnsFirstArg());
        when(accountRepositoryMock.findOne(id)).thenReturn(fakeAccount);
        when(accountRepositoryMock.findByName(name)).thenReturn(fakeAccountOptional);

        return accountRepositoryMock;

    }

    private static UserRepository getMockedUserRepository(Long id, String email, boolean emailExists, String name) {

        UserRepository userRepositoryMock = mock(UserRepository.class);

        User fakeUser = TestHelper.fakeUser(id, email, name);
        Optional<User> fakeUserOptional = emailExists ? Optional.of(fakeUser) : Optional.empty();

        when(userRepositoryMock.save(any(User.class))).thenAnswer(AdditionalAnswers.<String>returnsFirstArg());
        when(userRepositoryMock.findOne(id)).thenReturn(fakeUser);
        when(userRepositoryMock.findByEmail(email)).thenReturn(fakeUserOptional);

        return userRepositoryMock;

    }

}
