package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.*;
import org.tdb.security.AccountSecurity;
import org.tdb.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountSecurity accountSecurity;

    private UserRepository userRepository;

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account getAccountById(Long accountId) throws AccountServiceException {

        if (accountSecurity.hasAccessToAccount(accountId)) {
            return accountRepository.findOne(accountId);
        } else {
            throw new AccountServiceException(AccountServiceException.ErrorCode.NOT_AUTHORIZED);
        }
    }

    public AccountDTO getAccountDTOById(Long accountId) throws AccountServiceException {
        Account account = getAccountById(accountId);
        return account != null ? ModelMapperImpl.getAccountDTO(account) : null;
    }

    @Override
    public AccountDTO createAccountAndUser(String accountName, String email, String password)
            throws AccountServiceException {

        if (accountRepository.findByName(accountName).isPresent()) {
            throw new AccountServiceException(AccountServiceException.ErrorCode.ACCOUNT_NAME_TAKEN);
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new AccountServiceException(AccountServiceException.ErrorCode.EMAIL_IS_TAKEN);
        }

        User user = userRepository.save(new User(email, password));
        Account account = accountRepository.save(new Account(user, accountName));
        return ModelMapperImpl.getAccountDTO(account);

    }

}
