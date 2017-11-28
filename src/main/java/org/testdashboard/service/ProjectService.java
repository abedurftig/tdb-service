package org.testdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testdashboard.model.ModelMapperImpl;
import org.testdashboard.model.Project;
import org.testdashboard.model.ProjectDTO;
import org.testdashboard.model.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return projects.size() > 0 ?
                ModelMapperImpl.getProjectDTOs(projects) : new ArrayList<>();

    }

    public ProjectDTO getProjectByExternalId(String externalId) {

        Optional<Project> projectOptional = projectRepository.findByExternalId(externalId);
        if (projectOptional.isPresent()) {
            return ModelMapperImpl.getProjectDTO(projectOptional.get());
        } else {
            return null;
        }

    }

}
