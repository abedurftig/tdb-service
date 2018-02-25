package org.tdb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.tdb.model.*;

import java.util.Optional;

@Service
public class AccountSecurityImpl implements AccountSecurity {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountSecurityImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private TestRunRepository testRunRepository;

    public AccountSecurityImpl() {}

    @Override
    public boolean hasAccessToAccount(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findByOwner(getCurrentUser());
        if (accountOptional.isPresent()) {
            return accountId.equals(accountOptional.get().getId());
        }
        return false;
    }

    @Override
    public boolean hasAccessToProject(Long projectId) {
        Optional<Account> accountOptional = accountRepository.findByOwner(getCurrentUser());
        if (accountOptional.isPresent()) {
            Account account = projectRepository.getOwningAccount(projectId);
            return account != null ? account.equals(accountOptional.get()) : false;
        }
        return false;
    }

    @Override
    public boolean hasAccessToDashboard(Long dashboardId) {
        Optional<Account> accountOptional = accountRepository.findByOwner(getCurrentUser());
        if (accountOptional.isPresent()) {
            Account account = dashboardRepository.getOwningAccount(dashboardId);
            return account != null ? account.equals(accountOptional.get()) : false;
        }
        return false;
    }

    @Override
    public boolean hasAccessToTestRun(String externalId) {
        Optional<TestRun> testRunOptional = testRunRepository.findByExternalId(externalId);
        if (testRunOptional.isPresent()) {
            return hasAccessToProject(testRunOptional.get().getProject().getId());
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return userRepository.findByEmail(authentication.getPrincipal().toString()).get();
        } else {
            LOGGER.info("No authentication in security context.");
            return null;
        }
    }

    @Override
    public Account getCurrentAccount() {
        User user = getCurrentUser();
        if (user != null) {
            return accountRepository.findByOwner(user).get();
        } else {
            LOGGER.info("No authentication in security context.");
            return null;
        }
    }

    AccountSecurityImpl withTestRunRepository(TestRunRepository testRunRepository) {
        this.testRunRepository = testRunRepository;
        return this;
    }

    AccountSecurityImpl withAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        return this;
    }

    AccountSecurityImpl withProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        return this;
    }

}
