package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.upenn.cit594.util.Property;

public class PropertiesReader {

	private String filename;

	public PropertiesReader(String name) {
		this.filename = name;
	}
		
	public List<Property> getAllProperty(){
		
		// list for properties
		List<Property> properties =  new ArrayList<Property>();

		// open file
		try {
			File file = new File(this.filename);
			FileReader fr;
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String thisLine;
			String[] lineSplit;
			int livable_area_sq = 0;
			int market_value_sq = 0;
			int zip_code_sq = 0;
			
			String total_livable_area_str;
			String  market_value_str;
			String  zip_code_str;
			
			double total_livable_area;
			double market_value;
			String zip_code;
			
			
			try {
				
				thisLine = br.readLine();
				lineSplit = thisLine.split(",");
				
				// for each field in header, exam the sequence of the field
				for (int i=0; i<lineSplit.length; i++) {
					
					// if header is total livable area
					if (lineSplit[i].toLowerCase().contains("total")&&
							lineSplit[i].toLowerCase().contains("livable")&&
							lineSplit[i].toLowerCase().contains("area")) {
						livable_area_sq = i;
					}
					
					// if header is market value
					if (lineSplit[i].toLowerCase().contains("market")&&
							lineSplit[i].toLowerCase().contains("value")) {
						market_value_sq = i;
					}
					
					// if header is zip code
					if (lineSplit[i].toLowerCase().contains("zip")&&
							lineSplit[i].toLowerCase().contains("code")) {
						zip_code_sq = i;
					}
				}
					
				// read lines after header
				while((thisLine = br.readLine()) != null) {
					
					lineSplit = thisLine.split(",");
					
					// replace all non- alphanumeric char
					total_livable_area_str = lineSplit[livable_area_sq].replaceAll(
					          "[^a-zA-Z0-9]", "");
					market_value_str = lineSplit[market_value_sq].replaceAll(
					          "[^a-zA-Z0-9]", "");
					zip_code_str = lineSplit[zip_code_sq].replaceAll(
					          "[^a-zA-Z0-9]", "");
					
					// try to parse
					try {
						Integer.parseInt(zip_code_str);
						market_value = Double.parseDouble(market_value_str);
						total_livable_area = Double.parseDouble(total_livable_area_str);
						zip_code = zip_code_str.substring(0, 5);
						properties.add(new Property(zip_code, market_value, total_livable_area));
						
					}catch (Exception e) {
						continue;}

				}// end while loop
				
				
				br.close();
				
			} catch (IOException e) { //buffer reader read line fail
				// TODO Auto-generated catch block
				e.printStackTrace();}
			
			
			
			
		} catch (FileNotFoundException e) { //file reader fail
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		
		
		return properties;
		
	}
	
}
