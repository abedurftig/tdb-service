package org.tdb.service;

import org.tdb.model.UserDTO;

public interface UserService {

    UserDTO findUserByPrincipal(String principal);

}
