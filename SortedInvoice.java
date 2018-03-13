package com.ceg.ext;
import com.ceg.ext.dataContainer.*;
public class SortedInvoice {
	// Data members

	int currentSize; // current #of elements in the lsit
	int maxSize;  // maximum number of elements the list can hold
	Invoice[] elements; // storage for elements

	// Constructor
	public SortedInvoice(int maxElements) {
		currentSize =0;
		maxSize = maxElements;
		elements = new Invoice[maxSize];
	}

	public boolean isFull() {
		return (currentSize == maxSize);
	}
	
	public boolean isEmpty(){
		return (currentSize == 0);
	}

	public int size() {
		return (currentSize);
	}

	public int getMaxSize() {
		return(maxSize);
	}

	public int find(String invoice_code) {

		// initialize the return value to not found
		int location= -999;

		//Begin searching at the first element, to the last
		for (int i=0;  i <= this.currentSize-1; i++) {
			if (this.elements[i].getInvoiceCode() == invoice_code) {
				location = i;
				break;  // if found exit loop
			}
		} // end for

		return location;
	}// end find
	
	public void add(Invoice invoice) throws Exception{
		if(isFull()){
			throw new Exception("list full!");
		}
		
		int loc = 0;
		for(loc=0;loc<currentSize;loc++){
			if(elements[loc].getInvoiceTotal()<invoice.getInvoiceTotal()){
				break;
			}
		}
		
		for(int i=currentSize;i>loc;i--){
			elements[i]=elements[i-1];
		}
		elements[loc]=invoice;
		currentSize++;
	}
	
	public void remove(String invoiceCode) throws Exception{
		int loc=-1;
		for(int i=0;i<currentSize;i++){
			if(elements[i].getInvoiceCode()==invoiceCode){
				loc = i;
				break;
			}
		}
		if(loc==-1)
			throw new Exception("no such invoice.");
		for(int i=loc;i<currentSize-1;i++){
			elements[i]=elements[i+1];
		}
		currentSize --;
	}
	
	public void display(){
		System.out.println();
		System.out.println("Sorted invoice list:");
		System.out.println();
		for(int i=0;i<currentSize;i++){
			System.out.println("Invoice Code:"+elements[i].getInvoiceCode());
			System.out.println("Person ID:"+elements[i].getPersonID());
			System.out.println("Customer ID:"+elements[i].getCustomerID());
			System.out.println("Invoice Date:" +elements[i].getInvoiceDate());
			System.out.println("Invoice Total:"+elements[i].getInvoiceTotal());
			System.out.println("-----------------------------------------------");
		}
	}
	
}
