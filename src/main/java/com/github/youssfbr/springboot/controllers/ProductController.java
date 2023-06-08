package com.github.youssfbr.springboot.controllers;

import com.github.youssfbr.springboot.dtos.ProductDTO;
import com.github.youssfbr.springboot.dtos.ProductRecordDTO;
import com.github.youssfbr.springboot.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        List<ProductDTO> productsList = productService.findAll();
        if (!productsList.isEmpty()) {
            for (ProductDTO productDTO : productsList) {
                UUID id = productDTO.getId();
                productDTO.add(linkTo(methodOn(ProductController.class).findByIdAll(id)).withSelfRel());
            }
        }
        return productsList;
    }
    @GetMapping("/{id}")
    public ProductDTO findByIdAll(@PathVariable UUID id) {
        return productService.findById(id)
                .add(linkTo(methodOn(ProductController.class)
                        .findAll())
                        .withSelfRel());
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
