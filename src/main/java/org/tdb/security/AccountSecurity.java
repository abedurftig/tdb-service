package org.tdb.security;

import org.tdb.model.Account;
import org.tdb.model.User;

public interface AccountSecurity {

    boolean hasAccessToAccount(Long accountId);

    boolean hasAccessToProject(Long projectId);

    boolean hasAccessToDashboard(Long dashboardId);

    boolean hasAccessToTestRun(Long testRunId);

    User getCurrentUser();

    Account getCurrentAccount();

}
