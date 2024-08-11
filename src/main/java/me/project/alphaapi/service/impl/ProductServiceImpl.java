package me.project.alphaapi.service.impl;

import me.project.alphaapi.dto.ProductDTO;
import me.project.alphaapi.entity.Product;
import me.project.alphaapi.exception.NotFoundException;
import me.project.alphaapi.mapper.ProductMapper;
import me.project.alphaapi.repository.CategoryRepo;
import me.project.alphaapi.repository.ProductRepo;
import me.project.alphaapi.service.ProductService;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(Product product) {
        LOGGER.info("New product: {}", product);

        product.setCategory(categoryRepo.findByCategoryId(1L));

        LOGGER.info("Save product: {}", product);
        return productMapper.toDTO(productRepo.save(product));
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public ProductDTO findById(Long productId) {
        Optional<Product> product = productRepo.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("Product not found ID = " + productId);
        }

        LOGGER.info("Find product by id: {}", product.get());

        return productMapper.toDTO(product.get());
    }

    @Override
    public Product update(Product newProduct) {
        return null;
    }

    @Override
    public void deleteById(Long productId) {

    }
}
