package com.example.demo.service;

import com.borjaglez.springify.repository.filter.IPageFilter;
import com.example.demo.entity.Shop;
import com.example.demo.exception.DemoException;
import com.example.demo.utils.Constant;

public class AbstractShopService {
    
    protected void checkInputParams(IPageFilter pageFilter) {
        if (pageFilter.getPageNumber() == null) {
            throw new DemoException(Constant.PAGE_INDEX_REQUIRED);
        }
        if (pageFilter.getPageSize() == null) {
            throw new DemoException(Constant.PAGE_SIZE_REQUIRED);
        }
    }
    
    // SHOP DEMO SERVICES
    
    public Shop fromEditShopRequest(Shop shopRequest) {
        return new Shop(shopRequest.getId(), shopRequest.getName(),
                shopRequest.getDescription(), shopRequest.getAddress(),
                shopRequest.getCity(), shopRequest.getPhone(),
                shopRequest.getEmail(), shopRequest.getActiveStatus(),
                shopRequest.getUser(), shopRequest.getCategories(), shopRequest.getProducts());
    }

}
