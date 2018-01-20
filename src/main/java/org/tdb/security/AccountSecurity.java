package org.tdb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.tdb.model.*;

import java.util.Optional;

@Service
public class AccountSecurity {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountSecurity.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public boolean hasAccessToAccount(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findByOwner(getCurrentUser());
        if (accountOptional.isPresent()) {
            return accountId.equals(accountOptional.get().getId());
        }
        return false;
    }

    public boolean hasAccessToProject(Long projectId) {
        Optional<Account> accountOptional = accountRepository.findByOwner(getCurrentUser());
        if (accountOptional.isPresent()) {
            Account account = projectRepository.getOwningAccount(projectId);
            return account != null ? account.equals(accountOptional.get()) : false;
        }
        return false;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return userRepository.findByEmail(authentication.getPrincipal().toString()).get();
        } else {
            LOGGER.info("No authentication in security context.");
            return null;
        }
    }

}