package edu.upenn.cit594;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.datamanagement.CovidComparatorr;
import edu.upenn.cit594.datamanagement.CovidReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.CheckExtension;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.ui.Ui;

public class Main {

	public static void main(String[] args) {
		
		String pattern =  "^--(?<name>.+?)=(?<value>.+)$";
		Pattern r = Pattern.compile(pattern);
		
		/* Arraylist contaning already loaded input File*/
		List<String> used = new ArrayList<String>();
		/* Arraylist contaning proper Filenames used to compare with input files*/
		List<String> properNames = new ArrayList<String>();
		properNames.add("population");
		properNames.add("log");
		properNames.add("covid");
		properNames.add("property");
		/* store the file name to read */
		Map<String,String> filenames = new HashMap<String,String>();
		/* store any input datasets into data management tire */
		List<String> dataset = new ArrayList<String>();
		
		for(int i = 0; i < args.length; i++) {
			Matcher m = r.matcher(args[i]);

			if(m.find()) {
				
				/*The name of an argument is used more than once (e.g., "--log=a.log --log=a.log"). */
				if(used.contains(m.group(1))){
					System.out.println("error: The name of an argument is used more than once");
					return;
				}else {	
					used.add(m.group(1));
				}
				
				if(!properNames.contains(m.group(1))){
					System.out.println("error: The name of an argument is not one of the names listed above.  ");
					return;										
				}
				
				if(m.group(1).equals("population")) {
					try {
						File population = new File(m.group(2));
						filenames.put("population", m.group(2));
						if(!population.isFile()) {
							System.out.println("The specified population file does not exist");
							return;
						}
						
						else if(!population.canRead()) {
							System.out.println("Cannot open the specified population file or states file");
							return;
						}	
						
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				}
				
				else if(m.group(1).equals("log")) {	
					try {
						Logger logger = Logger.configuration(m.group(2));
						filenames.put("log", m.group(2));
					}catch (Exception e) {
				    	System.out.println("The program cannot create/open the specified log file");
						e.printStackTrace();
					}
				}
				
                else if(m.group(1).equals("covid")) {
                	if(!CheckExtension.getFileType(m.group(2)).toLowerCase().equals("csv") && !CheckExtension.getFileType(m.group(2)).toLowerCase().equals("json")) {
                		System.out.println("The format of the COVID data file can not be determined from the filename extension ('csv'\r\n"
                				+ "or 'json')");
                		return;
                	}
                	try {
						File covid = new File(m.group(2));
						filenames.put("covid", m.group(2));
						if(!covid.isFile()) {
							System.out.println("The specified covid file does not exist");
							return;
						}
						else if(!covid.canRead()) {
							System.out.println("Cannot open the specified covid file or states file");
							return;
						}	
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			
				
                else if(m.group(1).equals("property")) {
                	try {
						File properties = new File(m.group(2));
						filenames.put("property", m.group(2));
						if(!properties.isFile()) {
							System.out.println("The specified properties file does not exist");
							return;
						}
						
						else if(!properties.canRead()) {
							System.out.println("Cannot open the specified properties file or states file");
							return;
						}	
					} catch(Exception e) {
						e.printStackTrace();
					
				}
                }
				
				/* store the valid data set */
				if(!m.group(1).equals("log")) {
					dataset.add(m.group(1));
				}
				
				/* print out all the input file */
				System.out.println(m.group(2));


			/* Any arguments to main do not match the form “--name=value”. */				
			} else {
				System.out.println("error: One of the arguments to main do not match the form \"--name=value\" ");
				return;
			}
			
		}
		
		/* initialize the processor */
		Processor processor = new Processor(dataset);
		processor.setPopReader(new PopulationReader(filenames.get("population")));
		processor.setCovidReader(new CovidReader(filenames.get("covid")));
		PropertyProcessor prop_processor = new PropertyProcessor(new PropertiesReader(filenames.get("property")), new PopulationReader(filenames.get("population")));
		
		/* run the user interface function */
		Ui ui = new Ui(processor,prop_processor);
		
		

}
}
   
