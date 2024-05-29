package com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartItem {
    private long id;
    @NotNull
    @NotBlank(message = "Product id is required")
    private long productId;
    @NotNull
    @NotBlank(message = "quantity is required")
    @Min(value = 0, message = "Quantity must be non-negative")
    private int quantity;
}
