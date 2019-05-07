package com.app.library.service;

import com.app.library.model.User;
import com.app.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {

    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllReaders() {
        return findReadersByQueryIgnoreCase(null);
    }

    public List<User> findReadersByQueryIgnoreCase(String query) {
        return userRepository.findReadersByQuery(query != null ? query.toLowerCase() : "");
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
