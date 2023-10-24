package br.com.onlineStore.orderms.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "orders")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dt_order")
    private OffsetDateTime date;
    @NotNull
    private Set<Product> product;
    @NotNull
    @Column(name = "vl_order")
    private double value;
    @NotBlank
    private String TrackingCode;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
