package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ShopDTO;
import com.example.demo.dto.ShopGetDTO;
import com.example.demo.entity.Shop;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.service.IProductService;
import com.example.demo.service.IShopService;
import com.example.demo.utils.Constant;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(ShopsController.REQUEST_MAPPING)
public class ShopsController {
    public static final String REQUEST_MAPPING = "shops";
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopsController.class);
    
    @Autowired
    private IShopService shopService;
    
    @GetMapping(path = "/getShops")
    public @ResponseBody List<ShopGetDTO> findAll() {
        LOGGER.info("findAll in progress...");
        return shopService.findAll();
    }
    
    @GetMapping("/getShop")
    public ResponseEntity<?> getShops(@RequestParam(value = "id") Integer id) {
        LOGGER.info("getShop in progress...");
        ShopGetDTO shop = null;
        Map<String, Object> response = new HashMap<>();
        ResponseEntity<?> re = null;
        try {
            shop = shopService.getShop(id);
            if (shop == null) {
                response.put(Constant.MESSAGE, Constant.SHOP_NOT_EXISTS);
                response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
                re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            } else {
                response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
                re = new ResponseEntity<ShopGetDTO>(shop, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
            response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
            response.put(Constant.ERROR, e.getMessage());
            re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("getProducts is finished...");
        return re;
    }
    
    @PostMapping(path = "/createShop")
    @ResponseStatus(HttpStatus.CREATED)
    // @PreAuthorize("hasAnyAuthority('CONTACTS')")
    public ResponseEntity<?> createShop(@Valid @RequestBody ShopDTO createShopRequest, BindingResult result) {
        ShopDTO shopNew = null;
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED;
        String message = Constant.PRODUCT_CREATED;
        if (!result.hasErrors()) {
            try {
                shopNew = shopService.createShop(createShopRequest);
                response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());

            } catch (DataAccessException e) {
                response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
                response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            }

            response.put("shop", shopNew);
        } else {
            List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
            message = Constant.SHOP_NOT_CREATED;
            response.put(Constant.ERROR, errors);
            status = HttpStatus.BAD_REQUEST;
        }

        response.put(Constant.MESSAGE, message); //Meter en todos los controller
        return new ResponseEntity<Map<String, Object>>(response, status);
    }
    
    
}
