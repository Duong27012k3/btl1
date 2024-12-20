package com.nttemoi.warehouse.repositories;

import com.nttemoi.warehouse.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
    @Query("select s from Supplier s")
    List<Supplier> getListAll();
}
