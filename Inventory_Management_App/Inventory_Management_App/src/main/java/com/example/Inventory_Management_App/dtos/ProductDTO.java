package com.example.Inventory_Management_App.dtos;

public class ProductDTO {

    private String name;
    private String description;
    private int stock_quantity;
    private boolean low_threshold;

    public ProductDTO() {
    }

    public ProductDTO(String description, String name, int stock_quantity, boolean low_threshold) {
        this.description = description;
        this.name = name;
        this.stock_quantity = stock_quantity;
        this.low_threshold = low_threshold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }


    public boolean isLow_threshold() {
        return low_threshold;
    }

    public void setLow_threshold(boolean low_threshold) {
        this.low_threshold = low_threshold;
    }
}
