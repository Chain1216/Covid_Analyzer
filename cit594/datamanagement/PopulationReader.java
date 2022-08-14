package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.util.Population;

public class PopulationReader extends Reader{
	
	public PopulationReader(String name) {
		this.filename = name;
	}
	
	/*
	 * get an arraylist of population data from the input file
	 */
	public List<Population> getAllpop() {
		List<Population> Pops = new ArrayList<Population>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			int iteration = 0;
		    while((line = br.readLine())!= null) {
		    	if(iteration == 0) {
		    		iteration++;
		    		continue;
		    	}
		    	 String[] data = line.split(",");
			     String zip = data[0];
			     int last = zip.lastIndexOf("\"");
			     zip = zip.substring(1, last);
			     int pop = Integer.parseInt(data[1]);
			     Pops.add(new Population(zip,pop));
			}
		    br.close();
		}
		catch(Exception e) {
			throw new IllegalStateException(e);
		}
		return Pops;
			
		
	}
	
	
	

}
