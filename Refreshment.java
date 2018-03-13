package com.ceg.ext.dataContainer;

public class Refreshment extends Product{
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	private String name;
	private double cost;

	public Refreshment(String code,String name,double cost) {
		super(code);
		this.name=name;
		this.cost=cost;
		// TODO Auto-generated constructor stub
	}

}
