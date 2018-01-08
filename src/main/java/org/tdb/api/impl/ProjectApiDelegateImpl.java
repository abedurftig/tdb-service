package org.tdb.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.api.ProjectApiDelegate;
import org.tdb.model.Account;
import org.tdb.model.ProjectDTO;
import org.tdb.model.TestRunDTO;
import org.tdb.model.TestRunSummaryDTO;
import org.tdb.service.AccountService;
import org.tdb.service.ProjectService;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Arne
 * @since 26/11/2017
 */
@Component
public class ProjectApiDelegateImpl implements ProjectApiDelegate {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<List<TestRunSummaryDTO>> getProjectTestRuns(Long projectId, OffsetDateTime from, OffsetDateTime to) {
        return new ResponseEntity<>(
                projectService.getProjectTestRunsSummary(projectId),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDTO> createProject(ProjectDTO project) {
        Account account = accountService.getAccountById(project.getAccountId());
        ProjectDTO projectDTO = projectService.createProject(account, project.getName());

        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDTO> getProject(Long projectId) {
        return new ResponseEntity<>(
                projectService.getProjectDTO(projectId),
                HttpStatus.OK);
    }
}
