package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.List;
import edu.upenn.cit594.util.Property;

public class GetAverageMarketValue implements GetAverage{
	
	
	@Override
	public int getAverage(String zip_code, List<Property> dataset) {
		
		List<Property> data_with_zip = new ArrayList<Property>();
		double total_mv = 0;
		
		// test the validity of argument.
		if(zip_code.length() != 5) { 
			System.out.print("invalid zip_code");
			return 0;}
		
		try {
			Integer.parseInt(zip_code);
			
		}catch (Exception e) {
			System.out.print("invalid zip code");
			return 0;}
		
		// get all property with given zip code
		for (Property property: dataset) {
			if(property.getZip_code().equals(zip_code)) {
				data_with_zip.add(property);
			}
		} // end for loop
		
		// check if the list is empty (np property with input zip code)
		if(data_with_zip.isEmpty()) {
			return 0;
		}
		
		// get total market value
		for(Property p_zip: data_with_zip) {
			total_mv = total_mv + p_zip.getMarket_value();
		}
		
		int avg_mv = (int)(total_mv / data_with_zip.size());
		
		return avg_mv;
		
	}
	
	
	
	
	
	
	
	
}
