package org.tdb.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.tdb.model.Account;
import org.tdb.model.AccountBuilder;
import org.tdb.model.AccountRepository;
import org.tdb.model.UserRepository;
import org.tdb.service.AccountServiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceImplTests {

//    @Test
//    public void verifyGetAccountById() {
//
//        AccountRepository accountRepository =
//                getMockedAccountRepository(1L, "Test Account");
//        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, mock(UserRepository.class));
//
//        Account account = accountService.getAccountById(1L);
//
//        Assert.assertThat(account.getId(), Matchers.is(1L));
//        Assert.assertThat(account.getName(), Matchers.is("Test Account"));
//
//    }


    private static AccountRepository getMockedAccountRepository(Long id, String name) {

        AccountRepository accountRepositoryMock = mock(AccountRepository.class);

        Account fakeAccount = fakeAccount(id, name);

        when(accountRepositoryMock.save(fakeAccount)).thenReturn(fakeAccount);
        when(accountRepositoryMock.findOne(id)).thenReturn(fakeAccount);

        return accountRepositoryMock;

    }

    private static Account fakeAccount(Long id, String name) {
        return new AccountBuilder().withId(id).withName(name).create();
    }
}
