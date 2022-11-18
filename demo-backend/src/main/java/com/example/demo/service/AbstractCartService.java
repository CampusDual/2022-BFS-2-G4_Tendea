/**
 * @author adolfob
 */
package com.example.demo.service;

import com.borjaglez.springify.repository.filter.IPageFilter;
import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Cart;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.QuerySortPaginationRequest;
import com.example.demo.utils.Constant;

/**
 * 
 * @author adolfob
 *
 */
public class AbstractCartService {

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

	public Cart fromCreateCartRequest(CartDTO cartCreateRequest) {
		return new Cart(cartCreateRequest.getId(), cartCreateRequest.getComment(), cartCreateRequest.getCreateAt(),
				cartCreateRequest.getUpdatedAt(), cartCreateRequest.getUser(), cartCreateRequest.getItems());

	}

}
