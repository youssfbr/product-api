package com.github.youssfbr.springboot.controllers;

import com.github.youssfbr.springboot.dtos.ProductDTO;
import com.github.youssfbr.springboot.dtos.ProductRecordDTO;
import com.github.youssfbr.springboot.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }
    @GetMapping("/{id}")
    public ProductDTO findByIdAll(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO insert(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.insert(productRecordDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable UUID id, @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        return productService.update(id, productRecordDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id) {
        productService.delete(id);
    }

}
