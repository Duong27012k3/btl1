package com.nttemoi.warehouse.controlllers;

import com.nttemoi.warehouse.entities.Product;
import com.nttemoi.warehouse.entities.Productbom;
import com.nttemoi.warehouse.entities.Supplier;
import com.nttemoi.warehouse.services.ProductService;
import com.nttemoi.warehouse.services.ProductbomService;
import com.nttemoi.warehouse.services.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping ("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductbomService productbomService;
    @Autowired
    private SupplierService supplierService;

    // Trang hiển thị danh sách sản phẩm
    @GetMapping("/product")
    public String viewProduct (Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "show-product";
    }

    @RequestMapping(value="/product/detail/{id}", method=RequestMethod.GET)
    public String viewProductbom (@PathVariable Long id, Model model) {
        List<Productbom> productbomList = productService.findById(id).getProductbomlist();
        model.addAttribute("productbomList", productbomList);
        return "show-productbom";
    }


    @RequestMapping (value="/product/add", method=RequestMethod.GET)
    public String addProduct (Model model)  {
        Product product = new Product();
        List<Supplier> suppliers = supplierService.listAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("product", product);
        return "add-product";
    }

    @RequestMapping(value ="/product/save", method=RequestMethod.GET)
    public String saveProduct (@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/product";
    }
    @RequestMapping(value = "/product/delete/{id}")
    public String deleteProduct (@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product";
    }

    @RequestMapping(value ="/product/edit/{id}", method = RequestMethod.GET)
    public String editProductAndProductbom (@PathVariable Long id, Model model) {
        Product existingProduct = productService.findById(id);
        List<Productbom> productbomList = existingProduct.getProductbomlist();
        if(productbomList==null){
            productbomList =new ArrayList<>();
        }
        List<Supplier> suppliers = supplierService.listAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("product", existingProduct);
        model.addAttribute("productbomList", productbomList);
        return "edit-product";
    }

    @GetMapping("/product/submit/{id}")
    public String editing(@PathVariable Long id, @ModelAttribute("product") Product product,
                          @RequestParam(value ="newName[]", required = false) List<String> name,
                          @RequestParam(value ="newQuantity[]",required = false)List<String> quantity,
                          @RequestParam(value ="newCreatedAt[]",required = false) List<String> createdAt,
                          @RequestParam(value ="newUnit[]",required = false) List<String> unit,
                          @RequestParam(value ="oldName[]", required = false) List<String> oldName,
                          @RequestParam(value ="oldQuantity[]",required = false)List<String> oldQuantity,
                          @RequestParam(value ="oldCreatedAt[]",required = false) List<String> oldCreatedAt,
                          @RequestParam(value ="oldUnit[]",required = false) List<String> oldUnit
    ){
      if(oldName!=null){
          for(int i=0;i<oldName.size();i++){
              Productbom newProductbom = new Productbom(oldName.get(i),Long.parseLong(oldQuantity.get(i)), LocalDate.parse(oldCreatedAt.get(i)),oldUnit.get(i));
              newProductbom.setProduct(product);
              product.getProductbomlist().add(newProductbom);
          }
      }
      if(name!=null){
          for(int i=0;i<name.size();i++){
              Productbom newProductbom = new Productbom(name.get(i),Long.parseLong(quantity.get(i)),LocalDate.parse(createdAt.get(i)),unit.get(i));
              newProductbom.setProduct(product);
              product.getProductbomlist().add(newProductbom);
          }
      }
      productService.save(product);
      return "redirect:/product";
    }
//    @PostMapping("/save")
//    public String saveProduct (@ModelAttribute("product") Product product) {
//        productService.save(product);
//        return "redirect:/product/"; //return lai to rang product
//    }
//    @GetMapping ("/delete/{id}")
//    public String deleteProduct (@PathVariable long id) {
//        productService.deleteById(id);
//        return "redirect:/product/";
//    }
//    //Hiển thị các thành phần bom
//    @GetMapping("/productbom/{id}")
//    public String productBom(@PathVariable("id") Long id, Model model) {
//        Product product = productService.findById(id);
//        if (product != null) {
//            List<Productbom> listProductboms = productService.findAllBom();
//            model.addAttribute("listProductboms", product.getProductboms());
//            return "product-bom"; // View hiển thị chi tiết sản phẩm
//        } else {
//            return "redirect:/product/"; // Nếu không tìm thấy sản phẩm, quay lại danh sách
//        }
//    }
//    @GetMapping("/productbom/{id}")
//    public String detailProduct (@PathVariable Long id, Model model) {
//        List<Productbom> listProductboms = productService.findAllBom();
//        model.addAttribute("listProductboms", productService.findById(id));
//        return "product-bom";
//    }

}
