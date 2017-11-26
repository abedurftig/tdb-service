package org.testdashboard.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.testdashboard.model.AccountDTO;
import org.testdashboard.model.ProjectDTO;
import org.testdashboard.service.AccountService;
import org.testdashboard.service.ProjectService;

import java.util.List;

/**
 * @author Arne
 * @since 26/11/2017
 */
@Component
public class AccountApiDelegateImpl implements AccountApiDelegate {

    @Autowired
    AccountService accountService;

    @Autowired
    ProjectService projectService;

    @Override
    public ResponseEntity<AccountDTO> getAccount(Long accountId) {

        ResponseEntity<AccountDTO> responseEntity =
                new ResponseEntity<AccountDTO>(
                        accountService.getAccountById(accountId),
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
}
