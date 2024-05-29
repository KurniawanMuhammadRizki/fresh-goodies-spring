package com.fresh_goodies_spring.fresh_goodies_spring.Products.dto;

import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Metadata;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Image Url is required")
    private String imageUrl;

    @Min(value = 0, message = "price must be non-negative")
    private double price;

    @Min(value = 0, message = "Weight must non-negative")
    private int weight;

    @NotNull(message = "Metadata is required")
    private MetadataRequestDTO metadata;
}
