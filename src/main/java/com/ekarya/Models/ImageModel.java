package com.ekarya.Models;

import java.io.File;

public class ImageModel {
    private File ImgFile ; 
    private String propertyId ;

    
    public ImageModel(String propertyId ,File imgFile) {
        ImgFile = imgFile;
        this.propertyId = propertyId;
    }
    public File getImgFile() {
        return ImgFile;
    }
    public void setImgFile(File imgFile) {
        ImgFile = imgFile;
    }
    public String getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    
}
