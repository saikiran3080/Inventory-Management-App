package com.example.Inventory_Management_App.dtos;

import com.example.Inventory_Management_App.entities.Product;

import java.util.List;

public class ProductResponseDTO {
    List<ProductDTO> products;

    public ProductResponseDTO() {
    }

    public void addProducts(ProductDTO p)
    {
        products.add(p);

    }

    public ProductResponseDTO(List<ProductDTO> products) {
        this.products = products;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
