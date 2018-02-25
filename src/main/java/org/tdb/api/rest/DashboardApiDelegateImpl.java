package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.DashboardDTO;
import org.tdb.model.ErrorDTO;
import org.tdb.service.DashboardService;
import org.tdb.service.DashboardServiceException;

@Component
public class DashboardApiDelegateImpl implements DashboardApiDelegate {

    private DashboardService dashboardService;

    @Autowired
    public DashboardApiDelegateImpl(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Override
    public ResponseEntity<DashboardDTO> createDashboard(DashboardDTO dashboard) {
        try {
            DashboardDTO dashboardDTO = dashboardService.createDashboard(dashboard);
            return new ResponseEntity<>(dashboardDTO, HttpStatus.CREATED);
        } catch (DashboardServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }
    }

    @Override
    public ResponseEntity<Void> deleteDashboard(Long dashboardId) {
        try {
            dashboardService.deleteDashboard(dashboardId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DashboardServiceException e) {
            return ErrorResponseHelper.resolveFromServiceException(e);
        }
    }

}
