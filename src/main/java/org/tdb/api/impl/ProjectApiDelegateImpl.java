package org.tdb.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.api.ProjectApiDelegate;
import org.tdb.model.TestRunDTO;
import org.tdb.service.ProjectService;

import java.util.List;

/**
 * @author Arne
 * @since 26/11/2017
 */
@Component
public class ProjectApiDelegateImpl implements ProjectApiDelegate {

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<List<TestRunDTO>> getProjectTestRuns(Long projectId) {

                ResponseEntity<List<TestRunDTO>> responseEntity =
                new ResponseEntity<List<TestRunDTO>>(
                        projectService.getProjectTestRuns(projectId),
                        HttpStatus.OK);

        return responseEntity;

    }
}
