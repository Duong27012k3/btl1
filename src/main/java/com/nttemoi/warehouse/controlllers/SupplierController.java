package com.nttemoi.warehouse.controlllers;

import com.nttemoi.warehouse.entities.Supplier;
import com.nttemoi.warehouse.services.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
//@RequestMapping ("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService service;
    @RequestMapping("/supplier")
    public String supplier (Model model) {
        List<Supplier> listSuppliers = service.listAll();
        model.addAttribute("listSuppliers", listSuppliers);
        return "show-supplier";
    }
    @RequestMapping ("/supplier/add")
    public String addSupplier (Model model) {
        Supplier supplier= new Supplier();
        model.addAttribute("supplier1", supplier);
        return "add-supplier";
    }
    @RequestMapping(value = "/supplier/save", method = RequestMethod.POST)
    public String saveSupplier (@ModelAttribute("supplier1") Supplier supplier, Model model) {
        service.save(supplier);
        model.addAttribute("listSuppliers", service.listAll());
        System.out.println("1");
        return "redirect:/supplier"; //return lai to rang supplier
    }
    @RequestMapping ("/supplier/delete/{id}")
    public String deleteSupplier (@PathVariable long id) {
        service.deleteById(id);
        return "redirect:/supplier";
    }
}