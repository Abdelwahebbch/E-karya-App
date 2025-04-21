package com.ekarya.Models;

import java.sql.Date;

public class Booking 
{
    private int bookingId;
    private int landlordId;
    private int userId;
    private int propertyId;
    private Date startDate;
    private Date endDate;

    
    public Booking(int bookingId, int landlordId, int userId, int propertyId, Date startDate, Date endDate) {
        this.bookingId = bookingId;
        this.landlordId = landlordId;
        this.userId = userId;
        this.propertyId = propertyId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking()
    {
        this.bookingId = 0;
        this.landlordId = 0;
        this.userId = 0;
        this.propertyId = 0;
        this.startDate = null;
        this.endDate = null;

    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getLandlordId() {
        return landlordId;
    }
    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
