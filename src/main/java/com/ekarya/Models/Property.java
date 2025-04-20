package com.ekarya.Models;

import java.io.File;
import java.util.List;

/**
 * Data class to hold all the property information
 */
public class Property {
    private String id;
    private String title;
    private String location;
    private String description;
    private int guests;
    private int bedrooms;
    private int beds;
    private int bathrooms;
    private double price;
    private String landlord_id;
    private File mainImage;
    private List<File> additionalImages;

    public Property(String id, String title, String location, String description, int guests, int bedrooms, int beds,
            int bathrooms, double price, String landlord_id/* , File mainImage, List<File> additionalImages */) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.guests = guests;
        this.bedrooms = bedrooms;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.price = price;
        this.landlord_id = landlord_id;
        // this.mainImage = mainImage;
        // this.additionalImages = additionalImages;
    }

    public Property() {
        this.landlord_id = null;
        this.id = null;
        this.title = null;
        this.location = null;
        this.description = null;
        this.guests = 0;
        this.bedrooms = 0;
        this.beds = 0;
        this.bathrooms = 0;
        this.price = 0;
        // TODO Auto-generated constructor stub
    }

    // Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public File getMainImage() {
        return mainImage;
    }

    public void setMainImage(File mainImage) {
        this.mainImage = mainImage;
    }

    public List<File> getAdditionalImages() {
        return additionalImages;
    }

    public void setAdditionalImages(List<File> additionalImages) {
        this.additionalImages = additionalImages;
    }

    public String getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(String landlord_id) {
        this.landlord_id = landlord_id;
    }

    @Override
    public String toString() {
        return "PropertyData{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", guests=" + guests +
                ", bedrooms=" + bedrooms +
                ", beds=" + beds +
                ", bathrooms=" + bathrooms +
                ", price=" + price +
                ", mainImage=" + (mainImage != null ? mainImage.getName() : "none") +
                ", additionalImages=" + (additionalImages != null ? additionalImages.size() : 0) +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}