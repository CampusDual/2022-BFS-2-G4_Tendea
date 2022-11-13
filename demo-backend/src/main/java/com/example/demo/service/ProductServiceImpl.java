package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductGetDTO;
import com.example.demo.dto.mapper.ContactMapper;
import com.example.demo.dto.mapper.ProductGetMapper;
import com.example.demo.dto.mapper.ProductMapper;
import com.example.demo.entity.Category;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;

@Service
public class ProductServiceImpl extends AbstractProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> findAll() {
		return ProductMapper.INSTANCE.productToProductDTOList(productRepository.findAll());
	}

	/**
	 * Crea un producto
	 */
	@Override
	@Transactional
	public ProductDTO createProduct(ProductDTO productDTORequest) {
		Product product = ProductMapper.INSTANCE.productDTOtoProduct(productDTORequest);
		Product newProduct = productRepository.save(product);
		return ProductMapper.INSTANCE.productToProductDTO(newProduct);
	}

	/**
	 * Obtiene un producto por id
	 */
	@Override
	public ProductDTO getProduct(Integer id) {
		Product product = productRepository.findById(id).orElse(null);
		return ProductMapper.INSTANCE.productToProductDTO(product);
	}

	@Override
	public Integer deleteProduct(Integer id) {
		productRepository.deleteById(id);
		return id;
	}

	/**
	 * Devuleve el DTO paginado
	 */

	@Override
	@Transactional
	public DataSourceRESTResponse<List<ProductDTO>> getProducts(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Product> products = SpecificationBuilder.selectDistinctFrom(productRepository).where(pageFilter)
				.findAll(pageFilter);
		DataSourceRESTResponse<List<ProductDTO>> datares = new DataSourceRESTResponse<>();
		datares.setTotalElements((int) products.getTotalElements());
		List<ProductDTO> lProductDTO = ProductMapper.INSTANCE.productToProductDTOList(products.getContent());
		datares.setData(lProductDTO);
		return datares;
	}

	/**
	 * Edita un producto con el id (Esto viene de @AbstractProductService)
	 */
	@Override
	public Integer editProduct(ProductDTO editProductRequest) {
		Product productFromDTO = ProductMapper.INSTANCE.productDTOtoProduct(editProductRequest);
		Product editProduct = productRepository.save(fromEditProductRequest(productFromDTO));
		return editProduct.getId();
	}

	@Override
	public List<ProductDTO> findByCategory(Integer categoryId) {

		List<ProductDTO> products = new ArrayList<>();

		List<ProductDTO> productCategory = new ArrayList<>();

		products = ProductMapper.INSTANCE.productToProductDTOList(productRepository.findAll());

		for (ProductDTO p : products) {

			if (p.getCategory().getId() == categoryId) {

				productCategory.add(p);
			}

		}

		return productCategory;

	}

	/**
	 * Busqueda de productos por nombre
	 */
	@Override
	public List<ProductDTO> findByName(String query) {
		List<ProductDTO> products = new ArrayList<>();
		List<ProductDTO> productName = new ArrayList<>();

		products = ProductMapper.INSTANCE.productToProductDTOList(productRepository.findAll());

		for (ProductDTO p : products) {

			if (p.getName().toLowerCase().contains(query.toLowerCase())) {

				productName.add(p);
			}

		}
		return productName;
	}

	/**
	 * Busqueda por nombre, inicio, fin y sin diferencias de may y min
	 */

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findByNameContainingIgnoreCase(String query) {
		List<ProductDTO> products = new ArrayList<>();
		products = ProductMapper.INSTANCE
				.productToProductDTOList(productRepository.findByNameContainingIgnoreCase(query));
		return products;
	}


	/**
	 * Crea el producto de una tienda
	 */
	@Override
	public ProductDTO createProductStore(Product createProductRequest) {
		ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(createProductRequest);
		Product newProduct = productRepository.save(fromCreateProductStoreRequest(productDTO));
		return ProductMapper.INSTANCE.productToProductDTO(newProduct);
	}

}
