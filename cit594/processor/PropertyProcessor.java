package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.util.Population;
import edu.upenn.cit594.util.Property;

public class PropertyProcessor {
	
	private PropertiesReader proReader;
	private PopulationReader popReader;
	private List<Property> dataset;
	private GetAverage get_average;

	public PropertyProcessor(PropertiesReader proReader, PopulationReader popReader) {
		this.popReader = popReader;
		this.setProReader(proReader);
		this.dataset = proReader.getAllProperty();
	}
	
	
	
	public GetAverage getGetAverage() {
		return this.get_average;
	}
	
	public void setGetAverage(GetAverage get_average) {
		this.get_average = get_average;
	}

	public int getTotalMarketValuePerCapita(String zip_code) {
		List<Population> pop_dataset = popReader.getAllpop();
		List<Property> dataset = this.dataset;
		List<Property> data_with_zip = new ArrayList<Property>();
		int population_zip = 0;
		double total_mv = 0;
		int mv_percapita = 0;

		// test validity of input zip code
		if (zip_code.length() != 5) {
			System.out.print("invalid zip_code");
			return 0;
		}

		try {
			Integer.parseInt(zip_code);

		} catch (Exception e) {
			System.out.print("invalid zip code");
			return 0;
		}

		// get all property with given zip code
		for (Property property : dataset) {
			if (property.getZip_code().equals(zip_code)) {
				data_with_zip.add(property);
			}
		} // end for loop

		// check if the list is empty
		if (data_with_zip.isEmpty()) {
			return 0;
		}

		// get population with input zip code
		for (Population p : pop_dataset) {
			if (p.getZip_code().equals(zip_code)) {
				population_zip = p.getPopulation();
			}
		}

		// if no population with zip or population with zip = 0
		if (population_zip == 0) {
			return 0;
		}

		// get total market value
		for (Property p_zip : data_with_zip) {
			total_mv = total_mv + p_zip.getMarket_value();
		}

		mv_percapita = (int) (total_mv / population_zip);

		return mv_percapita;

	}
	
	//use of Strategy 
	public int executeAverageGetter(String zip_code) {
		
		return (get_average.getAverage(zip_code, dataset));
	}



	public PropertiesReader getProReader() {
		return proReader;
	}



	public void setProReader(PropertiesReader proReader) {
		this.proReader = proReader;
	}
	
	/*
	 * main里execute： 
	 *  get average livable area:
	 *   PropertyProcessor prop_p= new PropertyProcessor(new GetAverageLivableArea());
	 *   int avg_livable_are = prop_p.executeAverageGetter(String zip_code);
	 *   
	 *   get average market value:
	 *   PropertyProcessor mkt_p= new PropertyProcessor(new GetAverageMarketValue());
	 *   int avg_mkt_val = mkt_p.executeAverageGetter(String zip_code);
	 */
	
	
}
