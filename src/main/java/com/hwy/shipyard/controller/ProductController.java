package com.hwy.shipyard.controller;

import com.hwy.shipyard.dataobject.Product;
import com.hwy.shipyard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public Object add(@ModelAttribute Product product){
        return service.add(product);
    }

    @PostMapping("/update")
    public Object update(@ModelAttribute Product product){
        return service.update(product);
    }

    @GetMapping("/all")
    public Object getAll(Integer pageNum, Integer pageSize){
        return service.getAll(pageNum,pageSize);
    }

    @GetMapping("/getById")
    public Object getById(String productId){
        return service.getById(productId);
    }

    @GetMapping("/getByName")
    public Object getByName(String productName){
        return service.getByName(productName);
    }

    @GetMapping("/delete")
    public Object delete(String productId){
        return service.delete(productId);
    }

}
