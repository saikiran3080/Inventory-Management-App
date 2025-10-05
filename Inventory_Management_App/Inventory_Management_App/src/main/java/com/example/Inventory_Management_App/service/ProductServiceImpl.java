package com.example.Inventory_Management_App.service;

import com.example.Inventory_Management_App.dtos.ProductDTO;
import com.example.Inventory_Management_App.dtos.ProductResponseDTO;
import com.example.Inventory_Management_App.entities.Product;
import com.example.Inventory_Management_App.exception.BadRequestException;
import com.example.Inventory_Management_App.exception.NotFoundException;
import com.example.Inventory_Management_App.repositories.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;
    private int low_stock_threshold =5;


    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public ProductResponseDTO getAllProducts() {
       List<Product>  content = productRepo.findAll();

        List<ProductDTO> productDTOS = content.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();


        ProductResponseDTO result = new ProductResponseDTO(productDTOS);
       return result;

    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Couldn't find Product id: " + id));

        return modelMapper.map(product , ProductDTO.class);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO product) {
        if (product.getStock_quantity() < 0) {
            throw new BadRequestException("Stock quantity cannot be negative");
        }

        if(product.getStock_quantity()<5)
        {
            product.setLow_threshold(true);
        }
        Product product_db = modelMapper.map(product,Product.class);

        productRepo.save(product_db);

        return product;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product)
    {
        Optional<Product> result = productRepo.findById(id);
        Product existingProduct = null;

        if(result.isEmpty())
        {
            throw new NotFoundException("Couldn't find product");
        }
        else
        {
            existingProduct = result.get();
        }


        if (product.getStock_quantity() < 0) {
            throw new BadRequestException("Stock quantity cannot be negative");
        }

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock_quantity(product.getStock_quantity());
        existingProduct.setLow_threshold(product.getStock_quantity() < 5? true :false);

        productRepo.save(existingProduct);

        return modelMapper.map(existingProduct,ProductDTO.class);

    }

    @Override
    public String deleteProduct(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Couldn't find Product id: " + id));

        productRepo.delete(product);
        return "Deleted Product Id: " + id;
    }
//
//
    @Override
    public String increaseProductStock(Long id ,int quantity)
    {
        Optional<Product> result  = productRepo.findById(id);
        Product product = null;

        if(result.isEmpty())
        {
            throw new NotFoundException("Product not found to increase the stock");
        }
        else
        {
            product = result.get();
        }

        int new_stock = product.getStock_quantity()+quantity;

        product.setStock_quantity(new_stock);
        if(product.getStock_quantity()<5)
        {
            product.setLow_threshold(true);
        }
        else
        {
            product.setLow_threshold(false);
        }
        productRepo.save(product);

        return "Product id: "+id+" increased by quantity: "+quantity+" new stock quantity: "+new_stock;
    }

    @Override
    public String decreaseProductStock(Long id, int quantity) {
        Optional<Product> result  = productRepo.findById(id);
        Product product = null;

        if(result.isEmpty())
        {
            throw new NotFoundException("Product not found to increase the stock");
        }
        else
        {
            product = result.get();
        }

        if(product.getStock_quantity()<=0)
        {
            throw new BadRequestException("Cannot decrease stock below 0");
        }

        int new_stock = product.getStock_quantity()-quantity;

        product.setStock_quantity(new_stock);

        if(product.getStock_quantity()<5)
        {
            product.setLow_threshold(true);
        }
        else
        {
            product.setLow_threshold(false);
        }

        productRepo.save(product);

        return "Product id: "+id+" decreased by quantity: "+quantity+" new stock quantity: "+new_stock;
    }

    @Override
    public ProductResponseDTO getAllProductsLessThanThreshold() {
        List<Product> products = productRepo.findAll();

        List<ProductDTO> productDTOS = products.stream().filter(p -> p.getStock_quantity()<5)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(productDTOS);

        return productResponseDTO;
    }


}
