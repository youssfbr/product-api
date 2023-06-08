package com.github.youssfbr.springboot.services;

import com.github.youssfbr.springboot.dtos.ProductDTO;
import com.github.youssfbr.springboot.dtos.ProductRecordDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();
    ProductDTO insert(ProductRecordDTO productRecordDTO);

}
