package com.github.youssfbr.springboot.services;

import com.github.youssfbr.springboot.dtos.ProductDTO;
import com.github.youssfbr.springboot.dtos.ProductRecordDTO;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    List<ProductDTO> findAll();
    ProductDTO findById(UUID id);
    ProductDTO insert(ProductRecordDTO productRecordDTO);
    ProductDTO update(UUID id, ProductRecordDTO productRecordDTO);
    void delete(UUID id);

}
