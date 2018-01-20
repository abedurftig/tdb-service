package org.tdb.service;

import org.tdb.model.Account;
import org.tdb.model.AccountDTO;
import org.tdb.model.UserDTO;

public interface AccountService {

    Account getAccountById(Long accountId) throws AccountServiceException;

    AccountDTO getAccountDTOById(Long accountId) throws AccountServiceException;

    AccountDTO createAccountAndUser(String accountName, String email, String password)
            throws AccountServiceException;

}
