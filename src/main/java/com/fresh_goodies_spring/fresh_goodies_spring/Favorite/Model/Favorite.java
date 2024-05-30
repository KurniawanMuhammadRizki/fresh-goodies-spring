package com.fresh_goodies_spring.fresh_goodies_spring.Favorite.Model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    private long id;

    @NotNull
    private long productId;
}
