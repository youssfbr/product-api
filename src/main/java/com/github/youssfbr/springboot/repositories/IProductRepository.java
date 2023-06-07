package com.github.youssfbr.springboot.repositories;

import com.github.youssfbr.springboot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID> {
}
