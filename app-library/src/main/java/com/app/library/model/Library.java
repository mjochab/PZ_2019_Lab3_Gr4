package com.app.library.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.print.Book;

public class Library {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(targetEntity = User.class)
    private String director_id;


    @OneToOne(targetEntity = User.class)
    private String address_id;

    @OneToOne(targetEntity = Book.class)
    @Column(name = "name")
    @NotBlank
    @Length(max = 64)
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDirector_id() {
        return director_id;
    }

    public void setDirector_id(String director_id) {
        this.director_id = director_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
