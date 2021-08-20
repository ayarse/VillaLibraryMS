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
public class MembershipCard {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    private int id;
    private String barcode;
    private Date issuedAt;
    private Date expiresAt;
    private int userId;

    public MembershipCard(int id, String barcode, Date issuedAt, Date expiresAt, int userId) {
        this.id = id;
        this.barcode = barcode;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.userId = userId;
    }
    
}
