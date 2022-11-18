/**
 * @author adolfob
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cart;

/**
 * Cart Repository 
 * @author adolfob
 *
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
