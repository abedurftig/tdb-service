package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.*;
import org.tdb.service.*;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class ProjectApiDelegateImpl implements ProjectApiDelegate {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<List<TestRunSummaryDTO>> getProjectTestRuns(Long projectId, OffsetDateTime from, OffsetDateTime to) {

        try {
            return new ResponseEntity<>(
                    projectService.getProjectTestRunsSummary(projectId),
                    HttpStatus.OK);
        } catch (ProjectServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }
    }

    @Override
    public ResponseEntity<ProjectDTO> createProject(ProjectDTO project) {
        try {
            Account account = accountService.getAccountById(project.getAccountId());
            ProjectDTO projectDTO = projectService.createProject(account, project.getName());
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        } catch (AccountServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }
    }

    @Override
    public ResponseEntity<ProjectDTO> getProject(Long projectId) {

        try {
            return new ResponseEntity<>(
                    projectService.getProjectDTO(projectId),
                    HttpStatus.OK);
        } catch (ProjectServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }
    }

    @Override
    public ResponseEntity<Void> deleteProject(Long projectId) {
        try {
            projectService.deleteProject(projectId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProjectServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }
    }

    @Override
    public ResponseEntity<ProjectSummaryDTO> getProjectSummary(Long projectId) {
        try {
            ProjectSummaryDTO projectSummaryDto = projectService.getProjectSummaryDTO(projectId);
            return new ResponseEntity<>(projectSummaryDto, HttpStatus.OK);
        } catch (ProjectServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }
    }

}
