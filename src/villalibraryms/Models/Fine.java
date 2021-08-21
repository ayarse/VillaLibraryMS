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
public class Fine {

    public Fine(int id, int borrowId, Borrow borrow, Date fineDate, double fineAmount, Boolean paid, Date paidDate) {
        this.id = id;
        this.borrowId = borrowId;
        this.borrow = borrow;
        this.fineDate = fineDate;
        this.fineAmount = fineAmount;
        this.paid = paid;
        this.paidDate = paidDate;
    }

    private int id;
    private int borrowId;
    private Borrow borrow;
    private Date fineDate;
    private double fineAmount;
    private Boolean paid;
    private Date paidDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public Date getFineDate() {
        return fineDate;
    }

    public void setFineDate(Date fineDate) {
        this.fineDate = fineDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

}
