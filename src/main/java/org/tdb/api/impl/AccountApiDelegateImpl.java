package org.tdb.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.api.AccountApiDelegate;
import org.tdb.model.AccountDTO;
import org.tdb.model.ProjectDTO;
import org.tdb.model.ProjectSummaryDTO;
import org.tdb.service.AccountService;
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

        ResponseEntity<AccountDTO> responseEntity =
                new ResponseEntity<AccountDTO>(
                        accountService.getAccountDTOById(accountId),
                        HttpStatus.OK);

        return responseEntity;

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
    public ResponseEntity<AccountDTO> createAccount(AccountDTO account) {
        return new ResponseEntity<>(
                accountService.createAccount(account.getName()),
                HttpStatus.OK);
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
