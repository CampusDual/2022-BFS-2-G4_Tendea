/**
 * @author adolfob
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;

/**
 * @author adolfob
 *
 */
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

}
