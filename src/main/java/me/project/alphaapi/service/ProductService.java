package me.project.alphaapi.service;

import me.project.alphaapi.dto.ProductDTO;
import me.project.alphaapi.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDTO save(Product product);
    List<Product> findAll();
    ProductDTO findById(Long productId);
    Product update(Product newProduct);
    void deleteById(Long productId);
}
