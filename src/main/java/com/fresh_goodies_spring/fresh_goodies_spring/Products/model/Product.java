package com.fresh_goodies_spring.fresh_goodies_spring.Products.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    private long id;

    @NotNull
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @Min(value = 0, message = "Price must be non-negative")
    private  double price;

    @Min(value = 0, message = "Weight must be non-negative")
    private int weight;

    private Metadata metadata;

}
