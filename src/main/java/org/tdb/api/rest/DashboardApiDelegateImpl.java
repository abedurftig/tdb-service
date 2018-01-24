package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.tdb.model.DashboardDTO;
import org.tdb.model.ErrorDTO;
import org.tdb.service.DashboardService;
import org.tdb.service.DashboardServiceException;

import java.util.function.Predicate;
import java.util.function.Supplier;

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
            return resolveFromDashboardServiceException(e);
        }
    }

    @Override
    public ResponseEntity<Void> deleteDashboard(Long dashboardId) {
        try {
            dashboardService.deleteDashboard(dashboardId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DashboardServiceException e) {
            return resolveFromDashboardServiceException(e);
        }
    }

    private ResponseEntity resolveFromDashboardServiceException(DashboardServiceException e) {

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(e.getMessage());
        errorDTO.setCode(e.getErrorCode().name());

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        switch ((DashboardServiceException.ErrorCode) e.getErrorCode()) {
            case NO_PROJECT_SELECTED:
            case PROJECT_DOES_NOT_EXIST:
                httpStatus = HttpStatus.BAD_REQUEST;
            case NAME_TAKEN:
                httpStatus = HttpStatus.CONFLICT;
            case NOT_AUTHORIZED:
                httpStatus = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity(errorDTO, httpStatus);
    }
}
