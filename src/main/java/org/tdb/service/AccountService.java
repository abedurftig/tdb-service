package org.tdb.service;

import org.tdb.model.Account;
import org.tdb.model.AccountDTO;

public interface AccountService {

    Account getAccountById(Long accountId);

    AccountDTO getAccountDTOById(Long accountId);

    AccountDTO createAccount(String name);

}
