package com.example.registrationlogindemo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Slide {
    private String title;
    private String imageUrl;
    private String description;

    public Slide(String title, String imageUrl, String description){
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
