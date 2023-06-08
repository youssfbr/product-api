package com.github.youssfbr.springboot.services;

import com.github.youssfbr.springboot.dtos.ProductDTO;
import com.github.youssfbr.springboot.dtos.ProductRecordDTO;
import com.github.youssfbr.springboot.entities.Product;
import com.github.youssfbr.springboot.repositories.IProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
    }

    @Override
    @Transactional
    public ProductDTO insert(ProductRecordDTO productRecordDTO) {

        Product product = new Product();
        BeanUtils.copyProperties(productRecordDTO, product);
        Product saved = productRepository.save(product);

        return new ProductDTO(saved);
    }
}
