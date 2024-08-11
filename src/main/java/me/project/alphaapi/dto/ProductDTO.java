package me.project.alphaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String name;

    private String SKU;

    private String description;

    private long price;

    private int stock;

    private String categoryName;
}
