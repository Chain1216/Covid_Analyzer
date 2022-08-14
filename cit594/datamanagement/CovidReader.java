ackage edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.upenn.cit594.util.Covid;


public class CovidReader extends Reader{
	public CovidReader(String name) {
		this.filename = name;
	}
	
	/*
	 * get an arraylist of covid data from the input file
	 */
	public List<Covid> getAllcovid_csv() {
		List<Covid> covids = new ArrayList<Covid>();
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
		    	 
		    	 /* skip the line if zipcode is unknown*/
		    	 if(data[0] == null) {
		    		 continue;
		    	 }
		    	 
			     String zip = data[0];
			     int partial = 0;
			     if(data[5]!="") {
			    	 partial = Integer.parseInt(data[5]);
			     }
			     
			     int fully = 0;
			     if(data[6]!="") {
			    	 fully = Integer.parseInt(data[6]);
			     }
			     
			     String[] time = data[8].split(" ");
			     String time2 = time[0]; 
			     time2 = time2.substring(1);
			     covids.add(new Covid(zip,partial,fully,time2));
			}
		    br.close();
		}
		catch(Exception e) {
			throw new IllegalStateException(e);
		}
		return covids;
			
		
	}
	
	
	public List<Covid> getAllcovid_json() {
		List<Covid> covids = new ArrayList<Covid>();
		
		try {
			JSONParser parser = new JSONParser();
			JSONArray arry = (JSONArray) parser.parse(new FileReader(filename));
			
			for(Object covid: arry) {
				JSONObject covid_data = (JSONObject) covid;
				
				String zip = "";
				if(covid_data.containsKey("zip_code")) {
					zip = String.valueOf(covid_data.get("zip_code"));
				}
				
				int partial = 0;
			     if(covid_data.containsKey("partially_vaccinated")) {
			    	 partial = Integer.parseInt(String.valueOf(covid_data.get("partially_vaccinated")));
			     }
			     
			     int fully = 0;
			     if(covid_data.containsKey("fully_vaccinated")) {
			    	 fully = Integer.parseInt(String.valueOf(covid_data.get("fully_vaccinated")));
			     }
			     
			     String time2 = "";
			     if(covid_data.containsKey("etl_timestamp")) {
			    	 String time = String.valueOf(covid_data.get("etl_timestamp")) ;
			    	 String[] time1 = time.split(" ");
			    	 time2 = time1[0];
			     }
			     
			     covids.add(new Covid(zip,partial,fully,time2));

			}
		}
		catch(Exception e) {
			throw new IllegalStateException(e);
		}
		return covids;
	}
	
	
	
	
}
