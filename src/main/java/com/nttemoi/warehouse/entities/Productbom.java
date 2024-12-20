package com.nttemoi.warehouse.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "productboms")
public class Productbom {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long quantity;

   // @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false)
   // @CreationTimestamp
    private LocalDate createdAt;

    @Column(nullable = false)
    private String unit;

    public Productbom(String name, long quantity, LocalDate createdAt, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.unit = unit;
    }
}
