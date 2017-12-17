package org.tdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.Account;
import org.tdb.model.AccountDTO;
import org.tdb.model.AccountRepository;
import org.tdb.model.ModelMapperImpl;
import org.tdb.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findOne(accountId);
    }

    public AccountDTO getAccountDTOById(Long accountId) {
        Account account = getAccountById(accountId);
        return account != null ? ModelMapperImpl.getAccountDTO(account) : null;
    }

    public AccountDTO createAccount(String name) {
        Account account = accountRepository.save(new Account(name));
        return ModelMapperImpl.getAccountDTO(account);
    }

}
