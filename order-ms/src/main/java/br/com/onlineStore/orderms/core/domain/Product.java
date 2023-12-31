package br.com.onlineStore.orderms.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codeProduct")
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    private Long codeProduct;
    private String name;
    private double price;
}
