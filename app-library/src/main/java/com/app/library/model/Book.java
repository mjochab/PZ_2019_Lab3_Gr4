package com.app.library.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(min = 1, max = 128)
    private String name;

    @Column(name = "publishing_company")
    @NotBlank
    @Length(min = 1, max = 64)
    private String publishingCompany;

    @NotBlank
    @Length(min = 1, max = 128)
    private String author;

    @Column(name = "year_of_publication")
    @Min(1800)
    private int yearOfPublication;

    @ManyToOne(optional = false)
    @JoinColumn(name = "library_id", updatable = false)
    @NotNull
    private Library library;

    @OneToMany(mappedBy = "book", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @NotEmpty
    private Set<BookUnit> bookUnits;

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<BookUnit> getBookUnits() {
        return bookUnits;
    }

    public void setBookUnits(Set<BookUnit> bookUnits) {
        this.bookUnits = bookUnits;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishingCompany='" + publishingCompany + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", author='" + author + '\'' +
                '}';
    }

    public static class Builder {

        private Integer id;
        private String name;
        private String publishingCompany;
        private int yearOfPublication;
        private int quantity = 0;
        private Library library;
        private String author;
        private Set<BookUnit> bookUnits;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder publishingCompany(String publishingCompany) {
            this.publishingCompany = publishingCompany;
            return this;
        }

        public Builder yearOfPublication(int yearOfPublication) {
            this.yearOfPublication = yearOfPublication;
            return this;
        }

        public Builder library(Library library) {
            this.library = library;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder quantity(int quantity) {
            if (quantity <= 0) {
                throw new RuntimeException("Quantity can not be lower than 0.");
            }

            this.bookUnits = IntStream.rangeClosed(1, quantity).mapToObj(el -> {
                BookUnit unit = new BookUnit();
                unit.setCheckedOut(false);

                return unit;
            }).collect(Collectors.toSet());

            return this;
        }

        public Book build() {
            Book book = new Book(id, name, publishingCompany, yearOfPublication, library, author, bookUnits);
            if (bookUnits != null && !bookUnits.isEmpty()) {
                bookUnits.forEach(el -> el.setBook(book));
            }
            return book;
        }
    }

    private Book(Integer id, String name, String publishingCompany, int yearOfPublication, Library library, String author, Set<BookUnit> bookUnits) {
        this.id = id;
        this.name = name;
        this.publishingCompany = publishingCompany;
        this.yearOfPublication = yearOfPublication;
        this.library = library;
        this.author = author;
        this.bookUnits = bookUnits;
    }
}
