package com.app.library.model;


import com.app.library.model.User;
import com.app.library.model.Library;

import javax.persistence.*;

@Entity
public class EmployeeOfLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private User user;

    @ManyToOne(optional = false)
    private Library library;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Library getLibrary() { return library; }

    public void setLibrary(Library library) { this.library = library; }

    @Override
    public String toString() {
        return "EmployeeOfLibrary{" +
                "id=" + id +
                ", user=" + user +
                ", library='" + library + '\'' +
                '}';
    }
}
