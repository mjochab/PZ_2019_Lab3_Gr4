package com.app.library.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Role role;

    @OneToOne(optional = false, cascade = { CascadeType.ALL })
    private Address address;

    // this field will be mapped to first_name by default, so we do not need to set up name in @Column
    @Column(name = "first_name")
    @NotBlank
    @Length(max = 64)
    private String firstName;

    @NotBlank
    @Length(max = 64)
    private String surname;

    @NotBlank
    @Length(max = 11, min = 11)
    @Column(unique = true)
    private String pesel;

    @NotBlank
    @Size(min = 1, max = 128)
    private String password;

    @Email
    @NotBlank
    @Size(min = 1, max = 64)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getFullName() {
        return String.format("%s %s", firstName, surname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}