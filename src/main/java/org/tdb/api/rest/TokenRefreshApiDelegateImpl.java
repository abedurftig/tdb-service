package org.tdb.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.tdb.model.UserDTO;
import org.tdb.service.UserService;

@Component
public class TokenRefreshApiDelegateImpl implements TokenRefreshApiDelegate {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDTO> refreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        UserDTO userDTO = userService
                .findUserByPrincipal(authentication.getPrincipal().toString());

        if (userDTO == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);

    }

}
