package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tdb.model.*;
import org.tdb.security.AccountSecurity;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AccountRepository accountRepository;

    private AccountSecurity accountSecurity;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository,
                              AccountSecurity accountSecurity) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountSecurity = accountSecurity;
    }

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository,
                              AccountSecurity accountSecurity, PasswordEncoder passwordEncoder) {
        this(accountRepository, userRepository, accountSecurity);
        this.passwordEncoder = passwordEncoder;
    }

    public Account getAccountById(Long accountId) throws AccountServiceException {

        if (accountSecurity.hasAccessToAccount(accountId)) {
            Optional<Account> accountOptional = accountRepository.findById(accountId);
            if (accountOptional.isPresent()) {
                return accountOptional.get();
            } else {
                throw AccountServiceException.withDoesNotExit();
            }

        } else {
            throw AccountServiceException.withNotAuthorized();
        }
    }

    public AccountDTO getAccountDTOById(Long accountId) throws AccountServiceException {
        Account account = getAccountById(accountId);
        return account != null ? ModelMapperImpl.getAccountDTO(account) : null;
    }

    @Override
    public AccountDTO createAccountAndUser(String accountName, String email, String password)
            throws AccountServiceException {

        Account account = createAccountAndUserInternal(accountName, email, password);
        return ModelMapperImpl.getAccountDTO(account);

    }

    Account createAccountAndUserInternal(String accountName, String email, String password)
            throws AccountServiceException {

        if (accountRepository.findByName(accountName).isPresent()) {
            throw AccountServiceException.withNameTaken();
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw AccountServiceException.withEmailTaken();
        }

        User user = userRepository.save(new User(email, passwordEncoder.encode(password)));
        Account account = accountRepository.save(new Account(user, accountName));
        return account;

    }

}
