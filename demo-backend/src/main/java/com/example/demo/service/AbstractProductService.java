package com.example.demo.service;

import com.borjaglez.springify.repository.filter.IPageFilter;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.QuerySortPaginationRequest;
import com.example.demo.utils.Constant;

public class AbstractProductService {

	protected void checkInputParams(IPageFilter pageFilter) {
		if (pageFilter.getPageNumber() == null) {
			throw new DemoException(Constant.PAGE_INDEX_REQUIRED);
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(Constant.PAGE_SIZE_REQUIRED);
		}
	}

	protected void checkInputParams(QuerySortPaginationRequest pageFilter) {
		if (pageFilter.getPageIndex() == null) {
			throw new DemoException(Constant.PAGE_INDEX_REQUIRED);
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(Constant.PAGE_SIZE_REQUIRED);
		}
	}

	
	public Product fromEditProductRequest(Product productRequest) {
		return new Product(productRequest.getId(), productRequest.getName(), productRequest.getPrice(),
				productRequest.getCreateAt(), productRequest.getImages(), productRequest.getDiscount(),
				productRequest.getBulk(), productRequest.getCategory(), productRequest.getDescription());
	}

	public Product fromCreateProductRequest(ProductDTO productRequest) {
		return new Product(productRequest.getId(), productRequest.getName(), productRequest.getPrice(),
				productRequest.getCreateAt(), productRequest.getImages(), productRequest.getDiscount(),
				productRequest.getBulk(), productRequest.getCategory(), productRequest.getDescription());
	}
	
	/**
	 * Request para crear el producto desde una tienda
	 * @param createProductStoreRequest
	 * @return
	 *
	 * @Since 15 nov 2022
	 * @author adolfob
	 */
	public Product fromCreateProductStoreRequest(ProductDTO createProductStoreRequest) {
		return new Product(createProductStoreRequest.getId(), createProductStoreRequest.getName(), createProductStoreRequest.getPrice(),
				createProductStoreRequest.getCreateAt(), createProductStoreRequest.getImages(), createProductStoreRequest.getDiscount(),
				createProductStoreRequest.getBulk(), createProductStoreRequest.getCategory(), createProductStoreRequest.getShop(), createProductStoreRequest.getDescription());
	}
	
	/**
	 * Request para editar el producto desde una tienda
	 * @param editProductStoreRequest
	 * @return
	 *
	 * @Since 15 nov 2022
	 * @author adolfob
	 */
	public Product fromEditProductStoreRequest(Product editProductStoreRequest) {
		return new Product(editProductStoreRequest.getId(), editProductStoreRequest.getName(), editProductStoreRequest.getPrice(), 
				editProductStoreRequest.getCreateAt(), editProductStoreRequest.getImages(), editProductStoreRequest.getDiscount(),
				editProductStoreRequest.getBulk(), editProductStoreRequest.getCategory(), editProductStoreRequest.getShop(), editProductStoreRequest.getDescription() );
	}

}
