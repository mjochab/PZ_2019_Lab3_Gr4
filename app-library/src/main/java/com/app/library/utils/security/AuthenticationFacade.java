package com.app.library.utils.security;

public interface AuthenticationFacade<T> {

    T login(String username, String password) throws Exception;
}
