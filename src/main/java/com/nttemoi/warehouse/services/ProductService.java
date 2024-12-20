package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.entities.Product;
import com.nttemoi.warehouse.entities.Productbom;
import com.nttemoi.warehouse.repositories.ProductRepository;
import com.nttemoi.warehouse.repositories.ProductbomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> findAll() {
        return repo.findAll();
    }
    public Product findById(Long id) {
        return repo.findById(id).orElse(null);
    }
    public void save(Product product) {
        repo.save(product);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
//    @Autowired
//    private ProductbomRepository productbomRepository;
//
//    public List <Product> findAll () {
//        return productRepository.findAll();
//    }
//
//    public List<Productbom> findAllBom () { return productbomRepository.findAll(); }
//
//
//    public Product findById (long id) {
//        return productRepository.findById(id).orElse(null);
//    }
//
//    public void save (Product product) {
//        productRepository.save(product);
//        List<Productbom> productboms = productbomRepository.findByProductId(product.getId());
//        for (Productbom productbom : productboms) {
//            productbom.setProduct(product);  // Đảm bảo rằng Productbom liên kết với Product đúng cách
//            productbomRepository.save(productbom); // Lưu Productbom
//        }
//    }
//
//    public void deleteById (Long id) {
//        List<Productbom> productboms = productbomRepository.findByProductId(id);
//        for (Productbom productbom : productboms) {
//            productbomRepository.delete(productbom);
//        }
//        productRepository.deleteById(id);
//
//    }

//    public void updateProduct(Product product, List<Productbom> productboms) {
//        // Kiểm tra nếu sản phẩm đã tồn tại trong DB
//        if (productRepository.existsById(product.getId())) {
//            // Lưu cập nhật sản phẩm
//            productRepository.save(product);
//
//            // Cập nhật các Productbom liên quan đến sản phẩm
//            for (Productbom productbom : productboms) {
//                productbom.setProduct(product);  // Đảm bảo rằng Productbom liên kết với Product đúng cách
//                productbomRepository.save(productbom); // Lưu Productbom
//            }
//        } else {
//            throw new RuntimeException("Product with ID " + product.getId() + " not found.");
//        }
//    }
}
