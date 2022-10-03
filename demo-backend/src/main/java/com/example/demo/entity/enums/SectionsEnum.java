package com.example.demo.entity.enums;

public enum SectionsEnum {
	CONTACTS("CONTACTS");
	private String value;
	private SectionsEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
