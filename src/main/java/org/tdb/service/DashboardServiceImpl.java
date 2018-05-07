package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.*;
import org.tdb.security.AccountSecurity;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardServiceImpl implements DashboardService {

    private AccountSecurity accountSecurity;

    private DashboardRepository dashboardRepository;

    private ProjectRepository projectRepository;

    @Autowired
    public DashboardServiceImpl(DashboardRepository dashboardRepository,
                                ProjectRepository projectRepository,
                                AccountSecurity accountSecurity) {
        this.dashboardRepository = dashboardRepository;
        this.projectRepository = projectRepository;
        this.accountSecurity = accountSecurity;
    }

    @Override
    public DashboardDTO createDashboard(DashboardDTO dashboardDTO) throws DashboardServiceException {

        Optional<Dashboard> dashboardOptional = dashboardRepository.findByName(dashboardDTO.getName());
        if (dashboardOptional.isPresent()) {
            throw DashboardServiceException.withNameTaken();
        }

        Account account = accountSecurity.getCurrentAccount();
        Dashboard dashboard = new Dashboard(dashboardDTO.getName(), account);
        List<Project> accountProjects = projectRepository.findByAccountId(account.getId());

        if (dashboardDTO.getItems() == null || dashboardDTO.getItems().size() == 0) {
            throw DashboardServiceException.withoutProject();
        }

        for (DashboardItemDTO dashboardItemDto : dashboardDTO.getItems()) {

            Optional<Project> projectOptional =
                    accountProjects.stream()
                            .filter(project -> project.getId().equals(dashboardItemDto.getProjectId())).findFirst();

            if (!projectOptional.isPresent()) {
                throw DashboardServiceException.withProjectDoesNotExist();
            }

            dashboard.addProject(projectOptional.get());

        }

        dashboard = dashboardRepository.save(dashboard);
        return ModelMapperImpl.getDashboardDTO(dashboard);

    }

    @Override
    public void deleteDashboard(Long dashboardId) throws DashboardServiceException {
        if (accountSecurity.hasAccessToDashboard(dashboardId)) {
            dashboardRepository.deleteById(dashboardId);
        } else {
            throw DashboardServiceException.withNotAuthorized();
        }
    }
}
