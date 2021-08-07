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

    int id;
    int borrowId;
    Borrow borrow;
    Date fineDate;
    double fineAmount;
    Boolean paid;
    Date paidDate;

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
