package entities;

import java.time.Instant;
import java.time.LocalDateTime;

public class Book extends BaseEntity {
    private String title;
    private String author;
    private String isbn;
    private int countPages;
    private int publicationYear;
    private Category category; //clé etrangere

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCountPages() {
        return countPages;
    }

    public void setCountPages(int countPages) {
        this.countPages = countPages;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    //constructeurs

    public Book() {
    }

    public Book(int id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String author, String isbn, int countPages, int publicationYear, Category category) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.countPages = countPages;
        this.publicationYear = publicationYear;
        this.category = category;
    }
}
