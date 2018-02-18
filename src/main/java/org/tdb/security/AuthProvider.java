package org.tdb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tdb.model.User;
import org.tdb.model.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthProvider.class);

    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        LOGGER.info("authenticating: {}", authentication.getPrincipal());

        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        Optional<User> userOptional = userRepository.findByEmail(
                email);
        if (userOptional.isPresent()) {

            User user = userOptional.get();
            if (true) {//(passwordEncoder.matches(password, user.getPassword())) {

                return new UsernamePasswordAuthenticationToken
                        (email, password, Collections.emptyList());

            } else {
                throw new BadCredentialsException("Username or password incorrect.");
            }

        } else {
            throw new BadCredentialsException("Username or password incorrect.");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
