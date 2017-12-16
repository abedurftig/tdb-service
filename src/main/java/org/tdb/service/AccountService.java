package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdb.model.Account;
import org.tdb.model.AccountDTO;
import org.tdb.model.AccountRepository;
import org.tdb.model.ModelMapperImpl;

/**
 * @author Arne
 * @since 26/11/2017
 */
@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

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
