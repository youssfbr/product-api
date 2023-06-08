package com.github.youssfbr.springboot.controllers;

import com.github.youssfbr.springboot.dtos.ProductDTO;
import com.github.youssfbr.springboot.dtos.ProductRecordDTO;
import com.github.youssfbr.springboot.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO insert(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.insert(productRecordDTO);
    }

}
