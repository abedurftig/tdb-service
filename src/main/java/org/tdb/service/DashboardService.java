package org.tdb.service;

import org.tdb.model.DashboardDTO;

public interface DashboardService {

    DashboardDTO createDashboard(DashboardDTO dashboardDTO) throws DashboardServiceException;

}
