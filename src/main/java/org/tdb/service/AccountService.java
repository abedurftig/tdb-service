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

    public AccountDTO getAccountById(Long accountId) {

        Account account = accountRepository.findOne(accountId);
        return ModelMapperImpl.getAccountDTO(account);

    }

}
