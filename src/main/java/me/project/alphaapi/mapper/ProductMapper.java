package me.project.alphaapi.mapper;

import me.project.alphaapi.dto.ProductDTO;
import me.project.alphaapi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getSKU(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getName()
        );
    }
}
