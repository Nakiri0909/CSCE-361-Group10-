package com.ceg.ext.dataContainer;

public  class Product {
	private int ID;
	private String type;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String code;
	public Product(String code){
		this.code=code;
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	//abstract public String getType();
	
	public Product(int pid, String pcode, String ptype){
		this.ID = pid;
		this.code = pcode;
		this.type = ptype;
	}
}
