package com.app.library.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private String secondName;

    @NotBlank
    @Length(max = 11, min = 11)
    @Column(unique = true)
    private String pesel;

    @NotBlank
    @Length(max = 64)
    private String mail;

    @NotBlank
    @Length(max = 64)
    private String code;

    @NotBlank
    @Length(max = 64)
    private String street;

    @NotBlank
    @Length(max = 64)
    private String city;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    @Length(max = 64)
    private String password;


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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", surnamne='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                '}';
    }
}