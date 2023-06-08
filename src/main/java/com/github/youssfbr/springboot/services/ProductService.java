package com.github.youssfbr.springboot.services;

import com.github.youssfbr.springboot.dtos.ProductDTO;
import com.github.youssfbr.springboot.dtos.ProductRecordDTO;
import com.github.youssfbr.springboot.entities.Product;
import com.github.youssfbr.springboot.repositories.IProductRepository;
import com.github.youssfbr.springboot.services.exceptions.DatabaseException;
import com.github.youssfbr.springboot.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    @Transactional(readOnly = true)
    public ProductDTO findById(UUID id) {
        Product entity = findProductById(id);
        return new ProductDTO(entity);
    }

    @Override
    @Transactional
    public ProductDTO insert(ProductRecordDTO productRecordDTO) {

        Product product = new Product();
        BeanUtils.copyProperties(productRecordDTO, product);
        Product productSaved = productRepository.save(product);

        return new ProductDTO(productSaved);
    }

    @Override
    @Transactional
    public ProductDTO update(UUID id, ProductRecordDTO productRecordDTO) {
        Product productToUpdate = findProductById(id);
        BeanUtils.copyProperties(productRecordDTO, productToUpdate);
        Product productUpdated = productRepository.save(productToUpdate);

        return new ProductDTO(productUpdated);
    }

    @Override
    public void delete(UUID id) {
        try {
            findProductById(id);
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private Product findProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found."));
    }

}
