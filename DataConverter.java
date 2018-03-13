package com.ceg.ext;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ceg.ext.dataContainer.Address;
import com.ceg.ext.dataContainer.Customer;
import com.ceg.ext.dataContainer.Invoice;
import com.ceg.ext.dataContainer.Person;
import com.ceg.ext.dataContainer.Product;

public class DataConverter {
	public static void main(String[] args) throws Exception{
		ShowSth();
		InvoiceData.removeAllPersons();
		InvoiceData.removeAllCustomers();
		InvoiceData.removeAllInvoices();
		InvoiceData.removeAllProducts();
		ShowSth();
	}
	
	public static void ShowSth() throws Exception{
	    ArrayList<Customer> CustomerList = InvoiceData.getAllCustomers();
	    ArrayList<Product> ProductList = InvoiceData.getAllProducts();
	    ArrayList<Person> PersonList = InvoiceData.getAllPersons();
	    ArrayList<Invoice> InvoiceList = InvoiceData.getAllInvoices();
	    
	    System.out.println();
	    System.out.println("Customer List:");
	    System.out.println();
	    for(Customer c:CustomerList){
	    	System.out.println("Name:"+c.getName());
	    }
	    
	    System.out.println();
	    System.out.println("Product List:");
	    System.out.println();
	    for(Product p : ProductList){
	    	System.out.println("Product Code:"+p.getCode());
	    	System.out.println("Product Type:"+p.getType());
	    }
	    
	    System.out.println();
	    System.out.println("Person List:");
	    System.out.println();
	    
	    for(Person p:PersonList){
	    	System.out.println("First Name:"+p.getFirstName());
	    	System.out.println("Last Name:"+p.getLastName());
	    }
	    
	    System.out.println();
	    System.out.println("Invoice List:");
	    System.out.println();
	    
	    for(Invoice i : InvoiceList){
	    	System.out.println("Invoice Code:"+i.getInvoiceCode());
	    	System.out.println("Customer:"+InvoiceData.getCustomerById(i.getCustomerID()).getName());
	    }
	}
}
