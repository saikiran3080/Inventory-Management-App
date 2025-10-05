package com.example.Inventory_Management_App.controller;

import com.example.Inventory_Management_App.dtos.ProductDTO;
import com.example.Inventory_Management_App.dtos.ProductResponseDTO;
import com.example.Inventory_Management_App.entities.Product;
import com.example.Inventory_Management_App.service.ProductService;
import com.example.Inventory_Management_App.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<ProductResponseDTO> getAllProducts()
    {
        ProductResponseDTO products =  productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id)
    {
       return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product)
    {
        ProductDTO savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO)
    {
        ProductDTO updatedProduct = productService.updateProduct(id , productDTO);
        return  ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {

        String message = productService.deleteProduct(id);

        return ResponseEntity.ok(message);

    }

    @PatchMapping("/{id}/increase")
    public ResponseEntity<String> increaseStock(@PathVariable long id , @RequestParam(defaultValue = "1") int quantity)
    {
        String result  = productService.increaseProductStock(id , quantity);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/decrease")
    public ResponseEntity<String> decraseStock(@PathVariable long id , @RequestParam(defaultValue = "1") int quantity)
    {
        String result  = productService.decreaseProductStock(id , quantity);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/threshold")
    public ResponseEntity<ProductResponseDTO> getProductsLowerThanThreshold()
    {
        ProductResponseDTO result  = productService.getAllProductsLessThanThreshold();
        return ResponseEntity.ok(result);
    }




}
