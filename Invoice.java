package com.ceg.ext.dataContainer;

import java.util.ArrayList;

public class Invoice {

	int invoiceID;
	String invoiceCode;
	int personID;
	int customerID;
	String invoiceDate;
	double invoiceTotal;
	
	ArrayList<Product> productList;
	
	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public Invoice(int id, String code, int pid, int cid,  String date){
		invoiceID = id;
		invoiceCode = code;
		personID = pid;
		customerID = cid;
		invoiceDate = date;
	}
	
	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public double getInvoiceTotal() {
		return invoiceTotal;
	}
	public void setInvoiceTotal(double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}
	
}
