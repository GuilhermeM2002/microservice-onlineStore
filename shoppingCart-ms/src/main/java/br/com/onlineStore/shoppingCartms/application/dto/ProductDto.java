package br.com.onlineStore.shoppingCartms.application.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long codeProduct;
    private String name;
    private double price;
    private int quantity;
}
