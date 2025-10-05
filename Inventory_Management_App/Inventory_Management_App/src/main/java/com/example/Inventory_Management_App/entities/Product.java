package com.example.Inventory_Management_App.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stock_quantity")
    private int stock_quantity;

    @Column(name = "low_threshold")
    private boolean low_threshold;

    public Product() {

    }

    public Product(String name, String description, int stock_quantity, boolean low_threshold) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock_quantity=" + stock_quantity +
                '}';
    }
}
