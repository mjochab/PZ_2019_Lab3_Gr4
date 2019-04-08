package com.app.library.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private Date dateOfRental;

    @Column
    @NotNull
    private Date dateOfReturn;

    @Column
    @NotNull
    private Byte prolongationNumber;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private BookUnit bookUnit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(Date dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Byte getProlongationNumber() {
        return prolongationNumber;
    }

    public void setProlongationNumber(Byte prolongationNumber) {
        this.prolongationNumber = prolongationNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookUnit getBookUnit() {
        return bookUnit;
    }

    public void setBookUnit(BookUnit bookUnit) {
        this.bookUnit = bookUnit;
    }



}
