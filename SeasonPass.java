package com.ceg.ext.dataContainer;

public class SeasonPass extends Product{
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	private String name;
	private String startDate;
	private String endDate;
	private double cost;

	public SeasonPass(String code,String name,String startDate,String endDate,double cost) {
		super(code);
		this.name=name;
		this.startDate=startDate;
		this.endDate=endDate;
		this.cost=cost;
		// TODO Auto-generated constructor stub
	}
	

}
