package com.ekarya.Models;

import java.io.File;
import java.util.List;

/**
 * Data class to hold all the property information
 */
public class Property {
    private String title;
    private String location;
    private String description;
    private int guests;
    private int bedrooms;
    private int beds;
    private int bathrooms;
    private double price;
    private File mainImage;
    private List<File> additionalImages;

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
}