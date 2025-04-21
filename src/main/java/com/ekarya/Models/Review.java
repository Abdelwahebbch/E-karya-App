package com.ekarya.Models;

public class Review {
    private String propertyId;
    private String userId;
    private int rating;
    private String comment;

    public Review(String propertyId, String userId, int rating, String comment) {
        this.propertyId = propertyId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
