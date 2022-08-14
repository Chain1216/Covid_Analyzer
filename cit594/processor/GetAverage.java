package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.util.Property;

public interface GetAverage {
	
	public int getAverage(String zip_code, List<Property> dataset);
}
