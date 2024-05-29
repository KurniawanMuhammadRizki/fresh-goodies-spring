package com.fresh_goodies_spring.fresh_goodies_spring.Cart.Model;

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
    @NotBlank
    private long productId;
    @NotNull
    @NotBlank
    private int quantity;
}
