package com.app.library.utils.security;

import com.app.library.model.enumerate.RoleName;

public class AuthorizationDto {

    private String firstName;
    private String surname;
    private String email;
    private String pesel;

    private RoleName roleName;

    public AuthorizationDto(String firstName, String surname, String email, String pesel, RoleName roleName) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.pesel = pesel;
        this.roleName = roleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPesel() {
        return pesel;
    }

    public RoleName getRoleName() {
        return roleName;
    }
}
