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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    @Cacheable(value = "ProductDTO")
    public List<ProductDTO> findAll() {
        return productRepo.findAll().stream()
                .map(p -> productMapper.toDTO(p))
                .collect(Collectors.toList());
    }


    @Cacheable(value = "ProductDTO", key = "#productId")
    @Override
    public ProductDTO findById(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found ID = " + productId));

        return productMapper.toDTO(product);
    }

    @Override
    @CachePut(value = "ProductDTO", key = "#newProduct.productId")
    public ProductDTO update(Product newProduct) {
        Product current = productRepo.findById(newProduct.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found ID = " + newProduct.getProductId()));

        current.setName(newProduct.getName());
        current.setDescription(newProduct.getDescription());
        current.setCategory(newProduct.getCategory());
        current.setPrice(newProduct.getPrice());
        current.setSKU(newProduct.getSKU());
        current.setStock(newProduct.getStock());

        return productMapper.toDTO(productRepo.save(current));
    }

    @Override
    @CacheEvict(value = "ProductDTO", key = "#productId")
    public void deleteById(Long productId) {
        productRepo.deleteById(productId);
    }
}
