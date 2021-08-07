/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Models;

import java.sql.Date;

/**
 *
 * @author ayasnasih
 */
public class BookItem extends Book {

    private int bookItemId;
    private int bookId;
    private String barcode;
    private Date borrowedDate;
    private Date returnedDate;

    public BookItem(int bookId, int rack_id, int subject_id, int author_id, String title, String publication_year, Boolean borrowable, String isbn, String publisher, int numberOfPages,
            int bookItemId, String barcode, Date borrowedDate, Date returnedDate) {
        super(bookId, rack_id, subject_id, author_id, title, publication_year, borrowable, isbn, publisher, numberOfPages);
        this.bookItemId = bookItemId;
        this.bookId = bookId;
        this.barcode = barcode;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;

    }

    public BookItem(int bookId, Rack rack, Subject subject, Author author, String title, String publication_year, Boolean borrowable, String isbn, String publisher, int numberOfPages,
            int bookItemId, String barcode, Date borrowedDate, Date returnedDate, int totalCopies) {
        super(bookId, rack, subject, author, title, publication_year, borrowable, isbn, publisher, numberOfPages, totalCopies);
        this.bookItemId = bookItemId;
        this.bookId = bookId;
        this.barcode = barcode;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    public BookItem(Book book, int bookItemId, String barcode, Date borrowedDate, Date returnedDate) {
        super(
                book.getId(), 
                book.getRack(), 
                book.getSubject(), 
                book.getAuthor(), 
                book.getTitle(), 
                book.getPublication_year(), 
                book.getBorrowable(), 
                book.getIsbn(), 
                book.getPublisher(), 
                book.getNumberOfPages(), 
                book.getTotalCopies()
        );
        this.bookItemId = bookItemId;
        this.bookId = book.getId();
        this.barcode = barcode;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    public int getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(int bookItemId) {
        this.bookItemId = bookItemId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
    
    public Boolean isAvailable() {
        return ((this.borrowedDate != null && this.returnedDate != null) || 
                (this.borrowedDate == null && this.returnedDate == null));
    }

}
