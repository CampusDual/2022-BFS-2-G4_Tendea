/**
 * @author adolfob
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.entity.ProductImage;

/**
 * @author adolfob
 *
 */
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>, JpaSpecificationExecutor<ProductImage> {

}
