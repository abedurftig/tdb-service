package org.tdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdb.model.*;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDTO findUserByPrincipal(String principal) {

        UserDTO userDTO = null;

        Optional<User> userOptional = userRepository.findByEmail(principal);
        if (userOptional.isPresent()) {

            User user = userOptional.get();
            Optional<Account> accountOptional = accountRepository.findByOwner(user);
            if (accountOptional.isPresent()) {

                userDTO = ModelMapperImpl.getUserDTO(user, accountOptional.get().getId());

            }

        }

        return userDTO;

    }
}
