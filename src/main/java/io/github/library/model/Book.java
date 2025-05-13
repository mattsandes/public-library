package io.github.library.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "book_tb")
public class Book implements Serializable {

    @Serial
    private final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookId;
    private String title;

    @Column(nullable = true)
    private String subtitle;
    private String authors;
    private String publisher;
    private LocalDate publishDate;

    public Book(UUID id, String title, String subtitle, String authors, String publisher, LocalDate publishDate) {
        this.bookId = id;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishDate = publishDate;
    }

    public Book(String title) {
        this.title = title;
    }

    public Book() {
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String author) {
        this.authors = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) && Objects.equals(title, book.title) && Objects.equals(subtitle, book.subtitle) && Objects.equals(authors, book.authors) && Objects.equals(publisher, book.publisher) && Objects.equals(publishDate, book.publishDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(bookId);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(subtitle);
        result = 31 * result + Objects.hashCode(authors);
        result = 31 * result + Objects.hashCode(publisher);
        result = 31 * result + Objects.hashCode(publishDate);
        return result;
    }
}
