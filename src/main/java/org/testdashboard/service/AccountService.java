package org.testdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testdashboard.model.*;

/**
 * @author Arne
 * @since 26/11/2017
 */
@Component
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountDTO getAccountById(Long accountId) {

        Account account = accountRepository.findOne(accountId);
        return ModelMapperImpl.getAccountDTO(account);

    }

}
