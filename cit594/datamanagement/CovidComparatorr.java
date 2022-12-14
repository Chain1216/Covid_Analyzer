package edu.upenn.cit594.datamanagement;

public class CovidComparatorr {
	
	/**
	 * This function is used to determine the file extension of the input file
	 * It will automatically set the right file reader for the processor to process
	 * @param filename
	 * @return
	 */
	public static boolean compare(String filename) {
		if(getFileType(filename).equals("csv")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static String getFileType(String fileName) {
		int index = fileName.lastIndexOf('.');
		if(index > 0) {
			String extension = fileName.substring(index+1);
			return extension;
		}
		return null;
	}

}
