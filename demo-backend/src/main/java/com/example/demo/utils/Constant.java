package com.example.demo.utils;

public class Constant {
    
	//Contact error
	public static final String NAME_REQUIRED ="NAME_REQUIRED";
	public static final String ID_REQUIRED ="ID_REQUIRED";
	public static final String SURNAME1_REQUIRED ="SURNAME1_REQUIRED";
	public static final String SURNAME2_REQUIRED ="SURNAME2_REQUIRED";
	public static final String PHONE_REQUIRED ="PHONE_REQUIRED";
	public static final String PHONE_INVALID ="PHONE_INVALID";
	public static final String PHONE_ALREADY_EXISTS ="PHONE_ALREADY_EXISTS";
	public static final String EMAIL_REQUIRED ="EMAIL_REQUIRED";
	public static final String EMAIL_INVALID ="EMAIL_INVALID";
	public static final String CONTACT_NOT_EXISTS ="CONTACT_NOT_EXISTS";
	public static final String CONTACT_ALREADY_EXISTS ="CONTACT_ALREADY_EXISTS";
	public static final String CONTACT_CONSTRAINT_VIOLATION ="CONTACT_CONSTRAINT_VIOLATION";
	public static final String UNKNOWN_ERROR ="UNKNOWN_ERROR";
	
	//Contact message
	public static final String CONTACT_CREATE_SUCCESS ="CONTACT_CREATE_SUCCESS";
	public static final String CONTACT_NOT_CREATED ="CONTACT_NOT_CREATED";
	public static final String CONTACT_EDIT_SUCCESS ="CONTACT_EDIT_SUCCESS";
	public static final String CONTACT_NOT_EDIT ="CONTACT_NOT_EDIT";
	public static final String CONTACT_DELETE_SUCCESS ="CONTACT_DELETE_SUCCESS";
	public static final String CONTACT_NOT_DELETE ="CONTACT_NOT_DELETE";
	
	//User message
	public static final String USER_CREATE_SUCCESS ="USER_CREATE_SUCCESS";
	public static final String USER_EDIT_SUCCESS ="USER_EDIT_SUCCESS";
	public static final String USER_DELETE_SUCCESS ="USER_DELETE_SUCCESS";
	
	//User error
	public static final String LOGIN_REQUIRED ="LOGIN_REQUIRED";
	public static final String NIF_REQUIRED ="NIF_REQUIRED";
	public static final String LOGIN_EXISTS ="LOGIN_EXISTS";
	public static final String NIF_MALFORMED ="NIF_MALFORMED";
	public static final String USER_NOT_EXISTS ="USER_NOT_EXISTS";
	public static final String NO_SECTIONS_ACCESS ="NO_SECTIONS_ACCESS";
	public static final String USER_CONSTRAINT_VIOLATION ="USER_CONSTRAINT_VIOLATION";
	public static final String USER_INCORRECT_SIZE ="INCORRECT_SIZE";
	public static final String USER_LETTERS_ONLY ="LETTERS_ONLY";
	public static final String USER_ALPHANUMERIC_ONLY ="ALPHANUMERIC_ONLY";
	public static final String USER_PASSWORD_REQUIRED ="PASSWORD_REQUIRED";
	public static final String USER_CREATION_DATE_REQUIRED ="CREATION_DATE_REQUIRED";
	public static final String USER_ACTIVE_STATUS_REQUIRED ="ACTIVE_STATUS_REQUIRED";
	
	//Products error
	public static final String PRODUCT_DELETE_SUCCESS ="PRODUCT_DELETE_SUCCESS";
	public static final String PRODUCT_CREATE_SUCCESS ="PRODUCT_CREATE_SUCCESS";
	public static final String PRODUCT_NOT_CREATED = "PRODUCT_NOT_CREATED";
	public static final String PRODUCT_CREATED ="PRODUCT_CREATED";
	public static final String PRODUCT_NOT_EXISTS ="PRODUCT_NOT_EXISTS";
	public static final String PRODUCT_ALREADY_EXISTS ="PRODUCT_ALREADY_EXISTS";
	public static final String PRODUCT_EDIT_SUCCESS ="PRODUCT_EDIT_SUCCESS";
	public static final String PRODUCT_NOT_EDIT ="PRODUCT_NOT_EDIT";
	
	
	//Profile error
	public static final String PROFILE_CONSTRAINT_VIOLATION ="PROFILE_CONSTRAINT_VIOLATION";
	
	// Shop error
	public static final String SHOP_INCORRECT_SIZE = "INCORRECT_SIZE";
	public static final String SHOP_NOT_EXISTS = "SHOP_NOT_EXISTS";
	public static final String SHOP_NOT_CREATED = "SHOP_NOT_CREATED";
	public static final String IMAGE_NOT_EXISTS = "IMAGE_NOT_EXISTS";
	
	// Shop message
	public static final String SHOP_DELETE_SUCCESS = "TIENDA ELIMINADA";
	public static final String SHOP_NOT_DELETE = "SHOP_NOT_DELETED";
	public static final String SHOP_EDIT_SUCCESS = "SHOP_EDIT_SUCCESS";
	public static final String SHOP_NOT_EDIT = "SHOP_NOT_EDIT";
	public static final String SHOP_CREATED = "TIENDA ACTUALIZADA";
	
	//Common error
	public static final String ID_NOT_EXISTS ="ID_NOT_EXISTS";
	public static final String UNAUTHORIZED_USER ="UNAUTHORIZED_USER";
	public static final String SIGNATURE_NOT_PENDING ="SIGNATURE_NOT_PENDING";
	public static final String DATABASE_QUERY_ERROR ="DATABASE_QUERY_ERROR";
	
	//pagination error
	public static final String PAGE_INDEX_REQUIRED ="PAGE_INDEX_REQUIRED";
	public static final String PAGE_SIZE_REQUIRED ="PAGE_SIZE_REQUIRED";
	
	public static final String MESSAGE = "responseMessage";
	public static final String RESPONSE_CODE = "responseCode";
	public static final String ERROR = "errors";
	public static final String PHONE_ERROR ="contacts_phone_key";
    public static final String CITY_REQUIRED = "CITY_NAME REQUIRED";
    
    //Image message
    public static final String IMAGE_UPLOADED = "IMAGE_UPLOADED";
    //Image error
    public static final String IMAGE_UPLOAD_ERROR = "IMAGE_UPLOAD_ERROR";

}
