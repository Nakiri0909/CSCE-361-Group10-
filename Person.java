package com.ceg.ext.dataContainer;


public class Person {
	private int id;
	private String code;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String firstName;
	private String lastName;
	private Address address; // Person class owns Address class as a field
	
	
	// Constructor
	public Person(int id, String code, String firstName, String lastName, Address address) {
		super();
		this.id = id;
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	
	// Getter and Setter methods
	public Address getAddress() {
		return this.address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
