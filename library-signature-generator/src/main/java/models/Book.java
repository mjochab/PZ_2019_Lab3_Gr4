package models;

import java.util.List;
import java.util.Objects;

public class Book {

    private Book() {
    }

    private String title;

    private String publishedCompany;

    private String author;

    private Integer publishedYear;

    private List<String> signatures;

    /**
     *
     * @return title of book - can not be null
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return published company - may be null
     */
    public String getPublishedCompany() {
        return publishedCompany;
    }

    /**
     *
     * @return author - may be null
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return published year - can not be null
     */
    public Integer getPublishedYear() {
        return publishedYear;
    }

    /**
     *
     * @return book signatures - can not be empty
     */
    public List<String> getSignatures() {
        return signatures;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publishedCompany='" + publishedCompany + '\'' +
                ", author='" + author + '\'' +
                ", publishedYear=" + publishedYear +
                ", signatures=" + signatures +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return title.equals(book.title) &&
                Objects.equals(publishedCompany, book.publishedCompany) &&
                Objects.equals(author, book.author) &&
                publishedYear.equals(book.publishedYear) &&
                signatures.equals(book.signatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publishedCompany, author, publishedYear, signatures);
    }

    public static class Builder {

        private String title;

        private String publishedCompany;

        private String author;

        private Integer publishedYear;

        private List<String> signatures;

        /**
         *
         * @param title can not be null
         * @return self
         */
        public Builder setTitle(String title) {
            this.title = Objects.requireNonNull(title);
            return this;
        }

        /**
         *
         * @param publishedCompany can be null
         * @return self
         */
        public Builder setPublishedCompany(String publishedCompany) {
            this.publishedCompany = publishedCompany;
            return this;
        }

        /**
         *
         * @param author can be null
         * @return self
         */
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        /**
         *
         * @param publishedYear can not be null
         * @return self
         */
        public Builder setPublishedYear(Integer publishedYear) {
            this.publishedYear = Objects.requireNonNull(publishedYear);
            return this;
        }

        /**
         *
         * @param signatures can not be null and empty
         * @return self
         */
        public Builder setSignatures(List<String> signatures) {
            this.signatures = Objects.requireNonNull(signatures);
            return this;
        }

        public Book build() {
            return new Book(title, publishedCompany, author, publishedYear, signatures);
        }
    }

    private Book(String title, String publishedCompany, String author, Integer publishedYear, List<String> signatures) {
        this.title = Objects.requireNonNull(title);
        this.publishedCompany = publishedCompany;
        this.author = author;
        this.publishedYear = Objects.requireNonNull(publishedYear);
        this.signatures = Objects.requireNonNull(signatures);
    }
}
