package com.app.library.model.enumerate;


import com.app.library.model.User;
import com.app.library.model.Library;

import javax.persistence.*;

@Entity
public class EmployeeOfLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private User userId;

    @ManyToOne(optional = false)
    private Library libraryId;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public User getUserId() { return userId; }

    public void setUserId(User userId) { this.userId = userId; }

    public Library getLibraryId() { return libraryId; }

    public void setLibraryId(Library libraryId) { this.libraryId = libraryId; }

    @Override
    public String toString() {
        return "EmployeeOfLibrary{" +
                "id=" + id +
                ", userId=" + userId +
                ", libraryId='" + libraryId + '\'' +
                '}';
    }
}
