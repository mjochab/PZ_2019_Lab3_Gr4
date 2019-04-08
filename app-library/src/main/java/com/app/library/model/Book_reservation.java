package com.app.library.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.Date;


    @Entity
    public class Book_reservation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotNull
        private Date created_at;

        @ManyToOne(targetEntity = User.class)
        @NotNull
        private int reserved_by_user;

        //@ManyToOne(targetEntity = Book.class)
        @NotNull
        private int book_id;

        @Length(max = 64)
        private Date expires_at;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Date getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Date created_at) {
            this.created_at = created_at;
        }

        public int getReserved_by_user() {
            return reserved_by_user;
        }

        public void setReserved_by_user(int reserved_by_user) {
            this.reserved_by_user = reserved_by_user;
        }

        public int getBook_id() {
            return book_id;
        }

        public void setBook_id(int book_id) {
            this.book_id = book_id;
        }

        public Date getExpires_at() {
            return expires_at;
        }

        public void setExpires_at(Date expires_at) {
            this.expires_at = expires_at;
        }
    }
