package com.ceg.ext.dataContainer;

public class ParkingPass extends Product{
	private double parkingFee;
    
	public double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}

	public ParkingPass(String code,double parkingFee) {
		super(code);
		this.parkingFee=parkingFee;
		// TODO Auto-generated constructor stub
	}

}
