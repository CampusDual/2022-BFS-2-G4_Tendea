package com.example.demo.service;

import java.util.List;
import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;

import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IProductService {
	
	/**
	 * Listado si paginacion.
	 * @return List<ProductDTO> 
	 */
	List<ProductDTO> findAll();
	
	
	/**
	 * Eliminar imagenes de un producto
	 */
	
	void deleteProductImage(Integer id);
	
	/**
	 * Crea un producto
	 * @param createProductRequest
	 * @return
	 */
	ProductDTO createProduct(ProductDTO createProductRequest);
	
	/**
	 * Crea el producto con una tienda.
	 * @param product
	 * @return
	 */
	ProductDTO createProductStore(ProductDTO createProductStoreRequest);

	/**
	 * Obtiene un producto con el id
	 * @param id
	 * @return
	 */
	ProductDTO getProduct(Integer id);
	
	/**
	 * Elimina un producto (Borrado Logico)
	 * @param id
	 * @return
	 */
	Integer deleteProduct(Integer id);
	
	/**
	 * Devuelve los productos que alguno de sus campos contenga la 'query'
	 * independientemente de las mayúsculas.
	 * 
	 * @param pageFilter filtro de la tabla
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	DataSourceRESTResponse<List<ProductDTO>> getProducts(AnyPageFilter pageFilter);
	
	/**
	 * Edita un contacto de la BD
	 * @param editProductRequest
	 * @return
	 */
	Integer editProduct(ProductDTO editProductRequest);
	
	/**
	 * Obtener productos por categoria
	 */
	List<ProductDTO> findByCategory(CategoryDTO category);
	
	/**
	 * Obtener producto por el nombre (Parametro de busqueda)
	 * @param name
	 * @return product
	 */
	List<ProductDTO> findByName(String query);
	

	List<ProductDTO> findByNameContainingIgnoreCase(String query);


	
	List<ProductDTO> findByShopId(Integer id);
	
	public DataSourceRESTResponse<List<ProductDTO>> findProductsByShopPag(int id, AnyPageFilter pageFilter);
	
	
	

}
