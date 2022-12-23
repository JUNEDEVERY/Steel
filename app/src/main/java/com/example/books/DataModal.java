package com.example.books;

public class DataModal {
    private String Title;
    private int Cost;
    private int ID;
    private int StockAvailability;
    private int AvailabilityInTheStore;
    private String Description;
    private String Rewiews;
    private String Image;

    public DataModal(int ID, String title, int cost, int stockAvailability, int availabilityInTheStore, String description, String rewiews, String image) {
        this.ID = ID;
        this.Title = title;
        this.Cost = cost;
        this.StockAvailability = stockAvailability;
        this.AvailabilityInTheStore = availabilityInTheStore;
        this.Description = description;
        this.Rewiews = rewiews;
        this.Image = image;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
