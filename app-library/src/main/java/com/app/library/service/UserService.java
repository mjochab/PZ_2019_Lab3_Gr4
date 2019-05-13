package com.app.library.service;

import com.app.library.model.User;
import com.app.library.repository.UserRepository;
import com.app.library.utils.security.AuthenticationFacade;
import com.app.library.utils.security.AuthorizationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {

    private UserRepository userRepository;
    private AuthenticationFacade<AuthorizationDto> authenticationFacade;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) { return userRepository.getOne(id); }

    public List<User> findAllReaders() {
        return findReadersByQueryIgnoreCase(null);
    }

    public List<User> findReadersByQueryIgnoreCase(String query) {
        return userRepository.findReadersByQuery(query != null ? query.toLowerCase() : "");
    }

    public AuthorizationDto loginUser(String email, String password) throws Exception {
        return authenticationFacade.login(email, password);
    }

    @Autowired
    public void setAuthenticationFacade(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
