package org.tdb.service;

import org.junit.Before;
import org.junit.Test;
import org.tdb.model.*;
import org.tdb.security.AccountSecurity;
import org.tdb.test.TestHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DashboardServiceImplTests {

    private DashboardServiceImpl dashboardService;

    @Before
    public void setUp() {

        /*
         * This wil create a service with the following dependencies
         * - a dashboard with the name 'Dashboard One' already exists
         * - by default there will be projects with id 1L and 2L
         * - the current account is 'Test Account' with id 1L
         */
        dashboardService = TestHelper.getMockedDashboardServiceImpl();

    }

    @Test
    public void createDashboard() {

        try {

            DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
            dashboardDTO.setProjectIds(Arrays.asList(1L, 2L));
            dashboardService.createDashboard(dashboardDTO);

        } catch (DashboardServiceException e) {

            fail("Should not throw an exception.");

        }

    }

    @Test
    public void createDashboardWithProjectDoesNotExist() {

        try {

            DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
            dashboardDTO.setProjectIds(Arrays.asList(3L));
            dashboardService.createDashboard(dashboardDTO);

        } catch (DashboardServiceException e) {

            assertThat(e.getErrorCode().name())
                    .isEqualTo(DashboardServiceException.ErrorCode.PROJECT_DOES_NOT_EXIST.name());
            assertThat(e.getMessage()).isEqualTo("A project which has been referenced does not exist.");

        }

    }

    @Test
    public void createDashboardWithoutProjectNull() {

        try {

            DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
            dashboardService.createDashboard(dashboardDTO);
            fail("Should not be able to create the dashboard because the list of project ids is null");

        } catch (DashboardServiceException e) {

            assertThat(e.getErrorCode().name())
                    .isEqualTo(DashboardServiceException.ErrorCode.NO_PROJECT_SELECTED.name());
            assertThat(e.getMessage()).isEqualTo("At least one project needs to be selected for the dashboard.");

        }

    }

    @Test
    public void createDashboardWithoutProjectEmpty() {

        try {

            DashboardDTO dashboardDTO = new DashboardDTO().name("Does not exist");
            dashboardDTO.setProjectIds(new ArrayList<>());
            dashboardService.createDashboard(dashboardDTO);
            fail("Should not be able to create the dashboard because the list of project ids is empty");

        } catch (DashboardServiceException e) {

            assertThat(e.getErrorCode().name())
                    .isEqualTo(DashboardServiceException.ErrorCode.NO_PROJECT_SELECTED.name());
            assertThat(e.getMessage()).isEqualTo("At least one project needs to be selected for the dashboard.");

        }

    }

    @Test
    public void createDashboardWithNameExisting() {

        try {

            DashboardDTO dashboardDTO = new DashboardDTO().name("Dashboard One");
            dashboardDTO.setProjectIds(Arrays.asList(1L, 2L));
            dashboardService.createDashboard(dashboardDTO);
            fail("Should not be able to create the dashboard because a dashboard with the same name exists.");

        } catch (DashboardServiceException e) {

            assertThat(e.getErrorCode().name())
                    .isEqualTo(DashboardServiceException.ErrorCode.NAME_TAKEN.name());
            assertThat(e.getMessage()).isEqualTo("A Dashboard with this name already exists.");

        }

    }

}
