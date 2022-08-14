package edu.upenn.cit594.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.GetAverageLivableArea;
import edu.upenn.cit594.processor.GetAverageMarketValue;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.processor.PropertyProcessor;

public class Ui {
	
	private Processor processor;
	private PropertyProcessor prop_processor;
	private Logger log;
	
	public Ui(Processor processor, PropertyProcessor prop_processor, Logger log) {
		this.processor = processor;
		this.prop_processor = prop_processor;
		this.log = log;
		start();
	}

	private void start() {
		boolean start = true;
		String pattern =  "(?<!\\d)\\d{5}(?!\\d)";
		Pattern r = Pattern.compile(pattern);
		
		
		while(start) {
			showmenu();
			
			String input = null;
	        Scanner scan = new Scanner(System.in);
	        
	        	input = scan.nextLine();
	        	List<String> validinput = new ArrayList<>(
	        			List.of("1","2","3","4","5","6","7"));
	        	
	        	if(!validinput.contains((input))) {
	        		System.out.println("Please type a valid input. Single number 1-7 only and must without any whitespace.");
	        		continue;
	        	}
	        	
	        	switch(input) {
	        	
	        	
	        	case "0":
	        		start = false;
	        		break;
	        	case "1":
	        		processor.PrintDataset();
	        		break;
	        	case "2":
	        		processor.PrintTotalPop();
	        		break;
	        	case "3":
	        		String choice= "";
	        		String date = "";
	        		boolean choice_start = true;
	        		while(choice_start) {
	        			System.out.println("Please specify fully vaccinations or partial vaccinations by typing: 'partial' 'full'");
		        		choice = scan.nextLine();
		        		log.write(choice);
		        		if(!choice.equals("partial")&&!choice.equals("full")) {
		        			System.out.println("Please type a valid indicator");
		        		}else {
		        			choice_start = false;
		        		}
	        		}
	        		
	        		boolean data_start = true;
	        		while(data_start) {
	        			System.out.println("Please specify the data. Form ####-##-##");
		        		date = scan.nextLine();
		        		log.write(date);
		        		if(!processor.checkdata(date)) {
		        			System.out.println("Typed data out of range or in Wrong format");
		        		}else {
		        			data_start = false;
		        		}
	        		}
	        		
	        		processor.PrintVaccination(choice, date);
	        		break;
	        		
	        		
	        	case "4":
	        		String zip_code;	        		
	        		Boolean choice_start1 = true;
	        		
	        		while(choice_start1) {
	        			System.out.println("Please enter an 5-digit ZIP Code");
		        		zip_code = scan.nextLine();
		        		log.write(zip_code);
		        		Matcher a = r.matcher(zip_code);
		        		if(!a.find()) {
		        			System.out.println("Input is invalid! please make sure 5-digit only.");
		        		}else {
		        			prop_processor.setGetAverage(new GetAverageMarketValue());
			        		int mkt_avg = prop_processor.executeAverageGetter(zip_code);
			        		System.out.println("-----BEGIN OUTPUT-----");
			        		System.out.println("The average market value of given ZIP code is : " + mkt_avg);
			        		System.out.flush();
			        		System.out.println("-----END OUTPUT-----");
			        		choice_start1 = false;
		        		}
	        			
	        		}	
	        		break;
	        		
	        	case "5":        		
	        		String zip_code_51;	        		
	        		Boolean choice_start2 = true;
	        		
	        		while(choice_start2) {
	        			System.out.println("Please enter an 5-digit ZIP Code");
		        		zip_code_51 = scan.nextLine();
		        		log.write(zip_code_51);
		        		Matcher b = r.matcher(zip_code_51);
		        		if(!b.find()) {
		        			System.out.println("Input is invalid! please make sure 5-digit only.");
		        		}else {
		        			prop_processor.setGetAverage(new GetAverageLivableArea());
			        		int la_avg = prop_processor.executeAverageGetter(zip_code_51);
			        		System.out.println("-----BEGIN OUTPUT-----");
			        		System.out.println("The average livable area of given ZIP code is : " + la_avg);
			        		System.out.flush();
			        		System.out.println("-----END OUTPUT-----");
			        		choice_start2 = false;
		        		}
	        			
	        		}	
	        		
	        		break;
	        		
				case "6":
					String zip_code_6;
					Boolean choice_start3 = true;
					
					while(choice_start3) {
	        			System.out.println("Please enter an 5-digit ZIP Code");
	        			zip_code_6 = scan.nextLine();
	        			log.write(zip_code_6);
						Matcher c = r.matcher(zip_code_6);
		        		if(!c.find()) {
		        			System.out.println("Input is invalid! please make sure 5-digit only.");
		        		}else {
		        			int mkt_perCap = prop_processor.getTotalMarketValuePerCapita(zip_code_6);
		        			System.out.println("-----BEGIN OUTPUT-----");
							System.out.println("The market value per capita of given ZIP code is : " + mkt_perCap);
							System.out.flush();
			        		System.out.println("-----END OUTPUT-----");
			        		choice_start3 = false;
		        		}
	        			
	        		}

					break;
					
				case "7":
					String zip_code_7;
					Boolean choice_start4 = true;
					
					while(choice_start4) {
	        			System.out.println("Please enter an 5-digit ZIP Code");
	        			zip_code_7 = scan.nextLine();
						Matcher d = r.matcher(zip_code_7);
						log.write(zip_code_7);
		        		if(!d.find()) {
		        			System.out.println("Input is invalid! please make sure 5-digit only.");
		        		}else {
		        			System.out.println("-----BEGIN OUTPUT-----");
		        			int af = processor.ratioOfMarketValueToVaccinated(zip_code_7);
		        			System.out.flush();
			        		System.out.println("-----END OUTPUT-----");
			        		choice_start4 = false;
		        		}
	        			
	        		}
					
					break;
				}
	
		}
		
	}
		
        
	
	private void showmenu() {
		System.out.println("0. Exit the program.");
        System.out.println("1. Show the available data sets.");
        System.out.println("2. Show the total population for all ZIP Codes.");
        System.out.println("3. Show the total vaccinations per capita for each ZIP Code for the specified date.");
        System.out.println("4. Show the average market value for properties in a specified ZIP Code.");
        System.out.println("5. Show the average total livable area for properties in a specified ZIP Code.");
        System.out.println("6. Show the total market value of properties, per capita, for a specified ZIP Code.");
        System.out.println("7. Show the results of your custom feature.");
	}
	
	}
