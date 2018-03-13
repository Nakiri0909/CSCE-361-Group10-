package com.ceg.ext.dataContainer;

public class Customer {
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContactPersonCode() {
		return ContactPersonCode;
	}

	public void setContactPersonCode(String contactPersonCode) {
		ContactPersonCode = contactPersonCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private int ID;
	private int personID;
	private String code;
	private String type;
	private String ContactPersonCode;
	private String name;
	private Address address;
	
	public Customer(int id,int personid,String code,String type,String contactpersoncode,String name,Address address){
		this.ID = id;
		this.personID = personid;
		this.code = code;
		this.type = type;
		this.ContactPersonCode = contactpersoncode;
		this.name = name;
		this.address = address;
	}
}
