/**
 * @author adolfob
 */
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ProductImageRepository;

/**
 * Implementacion de ProductImageService
 * @author adolfob
 *
 */
@Service
public class ProductImageServiceImpl implements IProductImageService {

	@Autowired
	private ProductImageRepository imgRepository;
	
	/**
	 * Elimna una imagen de la bd por id
	 */
	@Override
	public Integer deleteImage(Integer id) {
		imgRepository.deleteById(id);
		return id;
	}
	
	

}
