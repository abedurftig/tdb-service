package org.tdb.api.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tdb.model.DashboardDTO;
import org.tdb.model.DashboardItemDTO;
import org.tdb.model.ErrorDTO;
import org.tdb.service.DashboardService;
import org.tdb.service.DashboardServiceException;
import org.tdb.test.TestHelper;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DashboardApiDelegateImplTests {

    private DashboardApiDelegateImpl dashboardApiDelegate;

    @Before
    public void setUp() {

        /*
         * This wil create a service with the following dependencies
         * - a dashboard with the name 'Dashboard One' already exists
         * - by default there will be projects with id 1L and 2L
         * - the current account is 'Test Account' with id 1L
         */
        DashboardService dashboardService = TestHelper.getMockedDashboardServiceImpl();
        dashboardApiDelegate = new DashboardApiDelegateImpl(dashboardService);

    }

    @Test
    public void createDashboard() {

        DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
        dashboardDTO.setItems(Arrays.asList(new DashboardItemDTO().projectId(1L), new DashboardItemDTO().projectId(2L)));
        ResponseEntity responseEntity = dashboardApiDelegate.createDashboard(dashboardDTO);

        assertEquals("status", HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void createDashboardWithProjectDoesNotExist() {

        DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
        dashboardDTO.setItems(Arrays.asList(new DashboardItemDTO().projectId(3L)));
        ResponseEntity responseEntity = dashboardApiDelegate.createDashboard(dashboardDTO);

        assertEquals("status", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void createDashboardWithoutProjectNull() {

        DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
        ResponseEntity responseEntity = dashboardApiDelegate.createDashboard(dashboardDTO);

        assertEquals("status", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void createDashboardWithoutProjectEmpty() {

        DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
        dashboardDTO.setItems(new ArrayList<>());
        ResponseEntity responseEntity = dashboardApiDelegate.createDashboard(dashboardDTO);

        assertEquals("status", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void createDashboardWithNameExisting() {

        DashboardDTO dashboardDTO = new DashboardDTO().name("Dashboard One");
        dashboardDTO.setItems(new ArrayList<>());
        ResponseEntity responseEntity = dashboardApiDelegate.createDashboard(dashboardDTO);

        assertEquals("status", HttpStatus.CONFLICT, responseEntity.getStatusCode());

    }

}
