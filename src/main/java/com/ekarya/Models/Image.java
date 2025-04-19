package com.ekarya.Models;

public class Image {
    private Long id;
    private Long houseId;
    private String url;
    private String caption;
    private boolean isPrimary;
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getHouseId() {
        return houseId;
    }
    
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getCaption() {
        return caption;
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public boolean isPrimary() {
        return isPrimary;
    }
    
    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
    
    // Helper method for Oracle database which uses 1/0 for boolean
    public int isPrimaryAsInt() {
        return isPrimary ? 1 : 0;
    }
    
    public void setPrimaryFromInt(int value) {
        this.isPrimary = (value == 1);
    }
}