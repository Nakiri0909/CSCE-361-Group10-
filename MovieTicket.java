package com.ceg.ext.dataContainer;

public class MovieTicket extends Product {
	public MovieTicket(String code,String dateTime,String movieName,Address address,String screenNo,double pricePerUnit) {
		super(code);
		this.dateTime=dateTime;
		this.movieName=movieName;
		this.address=address;
		this.screenNo=screenNo;
		this.pricePerUnit=pricePerUnit;
		// TODO Auto-generated constructor stub
	}
	private String dateTime;
	private String movieName;
	private Address address;
	private String screenNo;
	private double pricePerUnit;
   // public MovieTicket(String code,String dateTime,String movieName,Address address,String screenNo,double pricePerUnit){
    //	super(code,dateTime,movieName,address,screenNo,pricePerUnit);
   // }


	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}


}
