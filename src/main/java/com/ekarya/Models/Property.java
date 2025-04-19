package com.ekarya.Models;

public class Property {

    private int id;
    private String title;
    private String location;
    private double price_per_night;
    private int max_guests;
    private int max_beds;
    private int max_bedrooms;
    private int max_bathrooms;
    private String description;

    public Property(int id, String title, String location, double price_per_night, int max_guests, int max_beds,
            int max_bedrooms, int max_bathrooms, String description) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.price_per_night = price_per_night;
        this.max_guests = max_guests;
        this.max_beds = max_beds;
        this.max_bedrooms = max_bedrooms;
        this.max_bathrooms = max_bathrooms;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(double price_per_night) {
        this.price_per_night = price_per_night;
    }

    public int getMax_guests() {
        return max_guests;
    }

    public void setMax_guests(int max_guests) {
        this.max_guests = max_guests;
    }

    public int getMax_beds() {
        return max_beds;
    }

    public void setMax_beds(int max_beds) {
        this.max_beds = max_beds;
    }

    public int getMax_bedrooms() {
        return max_bedrooms;
    }

    public void setMax_bedrooms(int max_bedrooms) {
        this.max_bedrooms = max_bedrooms;
    }

    public int getMax_bathrooms() {
        return max_bathrooms;
    }

    public void setMax_bathrooms(int max_bathrooms) {
        this.max_bathrooms = max_bathrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImageUrl'");
    }

    public String getPrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrice'");
    }

}
