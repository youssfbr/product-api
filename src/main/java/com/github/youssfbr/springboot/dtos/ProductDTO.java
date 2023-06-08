package com.github.youssfbr.springboot.dtos;

import com.github.youssfbr.springboot.entities.Product;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDTO {

    private UUID id;
    private String name;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Product entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
