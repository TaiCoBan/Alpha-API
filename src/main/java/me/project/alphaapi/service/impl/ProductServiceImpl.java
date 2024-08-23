package me.project.alphaapi.service.impl;

import me.project.alphaapi.configuration.kafka.KafkaProducer;
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

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public ProductDTO save(Product product) {
        LOGGER.info("New product: {}", product);

        kafkaProducer.sendSaveMessage(product);

        return productMapper.toDTO(product);
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
        LOGGER.info("New product: {}", newProduct);

        kafkaProducer.sendUpdateMessage(newProduct);

        return productMapper.toDTO(newProduct);
    }

    @Override
    @CacheEvict(value = "ProductDTO", key = "#productId")
    public void deleteById(Long productId) {
        LOGGER.info("Delete product: {}", productId);

        kafkaProducer.sendDeleteMessage(productRepo.findById(productId).orElseThrow(() -> new NotFoundException("Product not found ID = " + productId)));
    }
}
