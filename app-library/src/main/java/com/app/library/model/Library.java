package com.app.library.model;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Library {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    @NotNull
    private User user;



    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)

    @NotNull
    private Address address;

    @Column(name = "name")
    @Length(max = 64)
    @NotNull
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
