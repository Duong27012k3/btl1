package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.entities.Productbom;
import com.nttemoi.warehouse.repositories.ProductbomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductbomService {
    @Autowired
    private ProductbomRepository repo;

    public List<Productbom> findAll() {
        return repo.findAll();
    }
    public Productbom findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
