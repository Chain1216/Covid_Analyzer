package edu.upenn.cit594.util;

public class Property {
	
	private String zip_code;
	private double market_value = 0;
	private double total_livable_area = 0;
	
	public Property(String zip_code, double market_value, double total_livable_area) {
		this.zip_code = zip_code;
		this.market_value = market_value;
		this.total_livable_area = total_livable_area;
	}



	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public double getMarket_value() {
		return market_value;
	}

	public void setMarket_value(int market_value) {
		this.market_value = market_value;
	}

	public double getTotal_livable_area() {
		return total_livable_area;
	}

	public void setTotal_livable_area(int total_livable_area) {
		this.total_livable_area = total_livable_area;
	}

	

}
