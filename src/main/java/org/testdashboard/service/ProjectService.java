package org.testdashboard.service;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testdashboard.model.ModelMapperImpl;
import org.testdashboard.model.Project;
import org.testdashboard.model.ProjectDTO;
import org.testdashboard.model.ProjectRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Arne
 * @since 24/11/2017
 */
@Component
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public List<ProjectDTO> getAccountProjects(Long accountId) {

        List<Project> projects = projectRepository.findByAccountId(accountId);
        if (!projects.isEmpty()) {

            return ModelMapperImpl.getProjectDTOs(projects);

        } else {
            return new ArrayList<>();
        }

    }

}
