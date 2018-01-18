package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.*;
import org.tdb.service.AccountService;
import org.tdb.service.AccountServiceException;
import org.tdb.service.ProjectService;
import org.tdb.service.ProjectServiceException;

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
        } catch (ProjectServiceException pse) {
            ErrorDTO errorDTO = new ErrorDTO();
            if (pse.getErrorCode() == ProjectServiceException.ErrorCode.NOT_AUTHORIZED) {
                errorDTO.setMessage(pse.getMessage());
                errorDTO.setCode(pse.getErrorCode().name());
                return new ResponseEntity(errorDTO, HttpStatus.UNAUTHORIZED);
            } else {
                errorDTO.setMessage("We are sorry!");
                return new ResponseEntity(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public ResponseEntity<ProjectDTO> createProject(ProjectDTO project) {
        try {
            Account account = accountService.getAccountById(project.getAccountId());
            ProjectDTO projectDTO = projectService.createProject(account, project.getName());
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
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
    public ResponseEntity<ProjectDTO> getProject(Long projectId) {

        try {
            return new ResponseEntity<>(
                    projectService.getProjectDTO(projectId),
                    HttpStatus.OK);
        } catch (ProjectServiceException pse) {
            ErrorDTO errorDTO = new ErrorDTO();
            if (pse.getErrorCode() == ProjectServiceException.ErrorCode.NOT_AUTHORIZED) {
                errorDTO.setMessage(pse.getMessage());
                errorDTO.setCode(pse.getErrorCode().name());
                return new ResponseEntity(errorDTO, HttpStatus.UNAUTHORIZED);
            } else {
                errorDTO.setMessage("We are sorry!");
                return new ResponseEntity(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
