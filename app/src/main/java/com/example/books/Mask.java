package com.example.books;

import android.graphics.Bitmap;

public class Mask {

    private int id;
    private String Title;
    private int Cost;
    private int StockAvailability;
    private int AvailabilityInTheStore;
    private String Description;
    private String Rewiews;
    private String Image;

    public Mask(int id, String title, int cost, int stockAvailability, int availabilityInTheStore, String description, String rewiews, String image) {
        this.id = id;
        Title = title;
        Cost = cost;
        StockAvailability = stockAvailability;
        AvailabilityInTheStore = availabilityInTheStore;
        Description = description;
        Rewiews = rewiews;
        Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getStockAvailability() {
        return StockAvailability;
    }

    public void setStockAvailability(int stockAvailability) {
        StockAvailability = stockAvailability;
    }

    public int getAvailabilityInTheStore() {
        return AvailabilityInTheStore;
    }

    public void setAvailabilityInTheStore(int availabilityInTheStore) {
        AvailabilityInTheStore = availabilityInTheStore;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRewiews() {
        return Rewiews;
    }

    public void setRewiews(String rewiews) {
        Rewiews = rewiews;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
