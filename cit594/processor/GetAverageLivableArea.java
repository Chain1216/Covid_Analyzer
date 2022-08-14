package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.util.Property;

public class GetAverageLivableArea implements GetAverage{

	@Override
	public int getAverage(String zip_code, List<Property> dataset) {
		
		List<Property> data_with_zip = new ArrayList<Property>();
		double total_area = 0;
		
		// test validity of input zip code
		if(zip_code.length() != 5) { 
			System.out.print("invalid zip_code");
			return 0;}
		
		try {
			Integer.parseInt(zip_code);
			
		}catch (Exception e) {
			System.out.print("invalid zip code");
			return 0;}
		
		//get all property with given zip code
		for (Property property: dataset) {
			if(property.getZip_code().equals(zip_code)) {
				data_with_zip.add(property);
			}
		} // end for loop
		
		// check if the list is empty
		if(data_with_zip.isEmpty()) {
			return 0;
		}
		
		// get average livable area
		for(Property p_zip: data_with_zip) {
			total_area = total_area + p_zip.getTotal_livable_area();
		}
		
		int avg_area = (int)(total_area / data_with_zip.size());
		
		return avg_area;
		
	}

}
