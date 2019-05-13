package com.app.library.utils.security;

import com.app.library.model.User;
import com.app.library.model.enumerate.RoleName;
import com.app.library.repository.UserRepository;
import com.app.library.service.PersistenceService;
import com.app.library.utils.PersistenceKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade<AuthorizationDto> {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PersistenceService persistenceService;

    public AuthorizationDto login(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found exception");
        }

        boolean passwordsMatches = bCryptPasswordEncoder.matches(password, user.getPassword());
        if (!passwordsMatches) {
            throw new Exception("Bad credentials");
        }

        if (user.getRole().getName().equals(RoleName.ROLE_EMPLOYEE)) {
            persistenceService.addToStore(PersistenceKeys.LOGGED_EMPLOYEE, user);
        }
        if (user.getRole().getName().equals(RoleName.ROLE_READER)) {
            persistenceService.addToStore(PersistenceKeys.LOGGED_READER, user);
        }
        if (user.getRole().getName().equals(RoleName.ROLE_DIRECTOR)) {
            persistenceService.addToStore(PersistenceKeys.LOGGED_DIRECTOR, user);
        }

        return getLoggedUser(user);
    }

    private AuthorizationDto getLoggedUser(User user) {
        return new AuthorizationDto(user.getFirstName(), user.getSurname(), user.getEmail(), user.getPesel(), user.getRole().getName());
    }

    @Autowired
    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
