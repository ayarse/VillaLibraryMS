/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
 */
package villalibraryms.Models;

import java.sql.Date;

/**
 *
 * @author ayasnasih
 */
public class Borrow {

    private int id;
    private BookItem book;
    private User user;
    private Date borrowedDate;
    private Date returnedDate;

    public Borrow(int id, BookItem book, User user, Date borrowedDate, Date returnedDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookItem getBook() {
        return book;
    }

    public void setBook(BookItem book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}
