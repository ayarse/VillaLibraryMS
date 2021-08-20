/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
 */
package villalibraryms.Models;

/**
 *
 * @author ayasnasih
 */
public class Book {

    private int id;
    private int rack_id;
    private int subject_id;
    private int author_id;
    private String title;
    private String publication_year;
    private Boolean borrowable;
    private String isbn;
    private String publisher;
    private int numberOfPages;
    private int totalCopies;

    private Rack rack;
    private Author author;
    private Subject subject;

    public Book(int id, int rack_id, int subject_id, int author_id, String title, String publication_year, Boolean borrowable, String isbn, String publisher, int numberOfPages) {
        this.id = id;
        this.rack_id = rack_id;
        this.subject_id = subject_id;
        this.author_id = author_id;
        this.title = title;
        this.publication_year = publication_year;
        this.borrowable = borrowable;
        this.isbn = isbn;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
    }

    public Book(int id, Rack rack, Subject subject, Author author, String title, String publication_year, Boolean borrowable, String isbn, String publisher, int numberOfPages, int totalCopies) {
        this.id = id;
        this.rack = rack;
        this.subject = subject;
        this.author = author;
        this.title = title;
        this.publication_year = publication_year;
        this.borrowable = borrowable;
        this.isbn = isbn;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
        this.totalCopies = totalCopies;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRack_id() {
        return rack_id;
    }

    public void setRack_id(int rack_id) {
        this.rack_id = rack_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public Boolean getBorrowable() {
        return borrowable;
    }

    public void setBorrowable(Boolean borrowable) {
        this.borrowable = borrowable;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

}
