package me.project.alphaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ProductDTO")
public class ProductDTO implements Serializable {

    private Long productId;

    private String name;

    private String SKU;

    private String description;

    private long price;

    private int stock;

    private String categoryName;

    public ProductDTO(String name) {
        this.name = name;
    }
}
