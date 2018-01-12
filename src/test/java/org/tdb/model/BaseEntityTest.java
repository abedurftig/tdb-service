package org.tdb.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class BaseEntityTest {

    @Test
    public void testEqualsSameType() {

        Account account1 = new Account();
        account1.setId(1L);

        Account account2 = new Account();
        account2.setId(1L);

        Assert.assertThat(account1.equals(account2), Matchers.is(true));
        Assert.assertThat(account2.equals(account1), Matchers.is(true));

    }

    @Test
    public void testEqualsDifferentType() {

        Account account = new Account();
        account.setId(1L);

        Project project = new Project();
        project.setId(1L);

        Assert.assertThat(account.equals(project), Matchers.is(false));
        Assert.assertThat(project.equals(account), Matchers.is(false));

    }

}
