package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.*;
import org.tdb.service.AccountService;
import org.tdb.service.AccountServiceException;
import org.tdb.service.ProjectService;

import java.util.List;

@Component
public class AccountApiDelegateImpl implements AccountApiDelegate {

    private AccountService accountService;

    private ProjectService projectService;

    @Autowired
    public AccountApiDelegateImpl(AccountService accountService, ProjectService projectService) {
        this.accountService = accountService;
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<AccountDTO> getAccount(Long accountId) {

        try {
            return new ResponseEntity<AccountDTO>(
                    accountService.getAccountDTOById(accountId),
                    HttpStatus.OK);
        } catch (AccountServiceException e) {
            ErrorDTO errorDTO = new ErrorDTO();
            if (e.getErrorCode() == AccountServiceException.ErrorCode.NOT_AUTHORIZED) {
                errorDTO.setMessage(e.getMessage());
                errorDTO.setCode(e.getErrorCode().name());
                return new ResponseEntity(errorDTO, HttpStatus.UNAUTHORIZED);
            } else {
                errorDTO.setMessage("We are sorry!");
                return new ResponseEntity(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @Override
    public ResponseEntity<List<ProjectDTO>> getProjects(Long accountId) {

        ResponseEntity<List<ProjectDTO>> responseEntity =
                new ResponseEntity<List<ProjectDTO>>(
                        projectService.getAccountProjects(accountId),
                        HttpStatus.OK);

        return responseEntity;

    }

    @Override
    public ResponseEntity<AccountDTO> createAccount(AccountInformation accountInformation) {

        try {
            AccountDTO accountDTO = accountService.createAccountAndUser(accountInformation.getAccountName(),
                    accountInformation.getEmail(), accountInformation.getPassword());
            return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.CREATED);
        } catch (AccountServiceException ase) {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage(ase.getMessage());
            errorDTO.setCode(ase.getErrorCode().name());
            return new ResponseEntity(errorDTO, HttpStatus.CONFLICT);
        }

    }

    @Override
    public ResponseEntity<List<ProjectSummaryDTO>> getProjectsSummary(Long accountId) {

        ResponseEntity<List<ProjectSummaryDTO>> responseEntity =
                new ResponseEntity<List<ProjectSummaryDTO>>(
                        projectService.getAccountProjectsSummary(accountId),
                        HttpStatus.OK);

        return responseEntity;

    }



}
