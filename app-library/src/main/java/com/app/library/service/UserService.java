package com.app.library.service;

import com.app.library.model.Role;
import com.app.library.model.User;
import com.app.library.model.enumerate.RoleName;
import com.app.library.repository.RoleRepository;
import com.app.library.repository.UserRepository;
import com.app.library.utils.security.AuthenticationFacade;
import com.app.library.utils.security.AuthorizationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationFacade<AuthorizationDto> authenticationFacade;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public Role getReaderRole() {
        return roleRepository.findByName(RoleName.ROLE_READER);
    }

    public Role getEmployeeRole() {
        return roleRepository.findByName(RoleName.ROLE_EMPLOYEE);
    }

    public AuthorizationDto loginUser(String email, String password) throws Exception {
        return authenticationFacade.login(email, password);
    }

    public String getEncryptedPassword(String password) {
        if (password == null || password.trim().length() == 0) {
            throw new IllegalArgumentException("Password can not be null");
        }
        return bCryptPasswordEncoder.encode(password);
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
