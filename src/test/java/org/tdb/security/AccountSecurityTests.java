package org.tdb.security;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tdb.Application;
import org.tdb.model.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AccountSecurityTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    @Test
    public void hasAccessToAccount() {

        User user = userRepository.save(new User("AccountSecurityTests", "email1","password"));
        Account account = accountRepository.save(new Account(user, "AccountSecurityTests"));
        Project project = projectRepository.save(new Project(account, "AccountSecurityTests 1"));

        AccountSecurity accountSecurity = new MockAccountSecurity(accountRepository, user);

        Assert.assertThat("should have access to this account",
                accountSecurity.hasAccessToAccount(account.getId()), Matchers.is(true));

    }

    @Test
    public void doesNotHaveAccessToAccount() {

        User userOne = userRepository.save(new User("AccountSecurityTests", "email2","password"));
        User userTwo = userRepository.save(new User("AccountSecurityTests", "email3","password"));
        Account account = accountRepository.save(new Account(userOne, "AccountSecurityTests 2"));
        Project project = projectRepository.save(new Project(account, "AccountSecurityTests 3"));

        AccountSecurity accountSecurity = new MockAccountSecurity(accountRepository, userTwo);

        Assert.assertThat("should not have access to this account",
                accountSecurity.hasAccessToAccount(account.getId()), Matchers.is(false));

    }

    class MockAccountSecurity extends AccountSecurity {

        private User user = null;

        public MockAccountSecurity(AccountRepository accountRepository, User user) {
            super(accountRepository);
            this.user = user;
        }

        @Override
        public User getCurrentUser() {
            return user;
        }

    }

}
