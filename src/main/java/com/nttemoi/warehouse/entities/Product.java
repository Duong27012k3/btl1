package com.nttemoi.warehouse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double size;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deletedAt;

    @Column(nullable = false)
    private String status;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

//    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Outbound> outbounds;
//
//    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Inbound> inbounds;

    @OneToMany (mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Productbom> productbomlist = new ArrayList<>();

}
