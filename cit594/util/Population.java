package edu.upenn.cit594.util;

public class Population {
	
	private String zip_code;
	private int population;
	
	public Population (String zip_code, int population) {
		this.population = population;
		this.zip_code = zip_code;
	}

	public String getZip_code() {
		return zip_code;
	}

	public int getPopulation() {
		return population;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	
	

}
