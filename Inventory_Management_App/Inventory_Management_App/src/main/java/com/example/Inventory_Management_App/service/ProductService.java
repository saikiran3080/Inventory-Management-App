package com.example.Inventory_Management_App.service;

import com.example.Inventory_Management_App.dtos.ProductDTO;
import com.example.Inventory_Management_App.dtos.ProductResponseDTO;
import com.example.Inventory_Management_App.entities.Product;

import java.util.List;

public interface ProductService {
    public ProductResponseDTO getAllProducts();
    ProductDTO getProductById(Long id);
    public ProductDTO saveProduct(ProductDTO product);

    ProductDTO updateProduct(Long id, ProductDTO product);

    public String deleteProduct(Long id);
    public String increaseProductStock(Long id ,int quantity);
    public String decreaseProductStock(Long id , int quantity);
    public ProductResponseDTO getAllProductsLessThanThreshold();
}
