package com.fresh_goodies_spring.fresh_goodies_spring.Products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataRequestDTO {
    @NotBlank(message = "Unit is required")
    private String unit;

    @Min(value = 0, message = "Weight must be non-negative")
    private int weight;

    @Min(value = 0, message = "Calorie count must be non-negative")
    private int calorie;

    @Min(value = 0, message = "Proteins must be non-negative")
    private double proteins;

    @Min(value = 0, message = "Fats must be non-negative")
    private double fats;

    @Min(value = 0, message = "Increment must be non-negative")
    private int increment;

    @Min(value = 0, message = "Carbs must be non-negative")
    private int carbs;
}
