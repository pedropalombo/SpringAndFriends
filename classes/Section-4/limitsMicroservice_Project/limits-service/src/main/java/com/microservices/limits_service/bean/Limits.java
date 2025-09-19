package com.microservices.limits_service.bean;

// base Bean class for the definition of what 'Limits' is
public class Limits {
	
	private int minimum, maximum;
	
	// empty constructor so Spring can use it as a basis whenever needed
	public Limits() {
		super();
	}

	// -- Auto generated constructor, getters/setters, and toString() --
	public Limits(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	@Override
	public String toString() {
		return "Limits [minimum=" + minimum + ", maximum=" + maximum + "]";
	}
	// -- / --

}
