package it.uniroma1.lcl.dietrolequinte.utils;

import java.time.LocalDateTime;

/**
 * Utils for DietroLeQuinte user interface
 * @author Enrico Anello
 *
 */
public class DietroLeQuinteUtils {
	/** 
	 * Converts all spaces repeated more than once to single space
	 * @param before
	 * @return
	 */
	public static String normalizeInput(String before) {
		return before.trim().replaceAll(" +", " ");
	}
	
	/**
	 * Returns the command from the input string
	 * @param inputString
	 * @return
	 */
	public static String getInputCommand(String inputString) {
		String[] split = inputString.split(" ");
		if (split.length > 0) {
			return split[0].trim().toLowerCase();
		}
		return null;
	}
	
	/**
	 * Returns all parameters
	 * @param inputString
	 * @return
	 */
	public static String getParameters(String inputString) {
		String[] split = inputString.split(" ");
		if (split.length >= 2) {
			String parameters = "";
			for (int i = 1; i < split.length; i++) {
				parameters += split[i] + " ";
			}
			return parameters.substring(0, parameters.length()-1);
		}
		return null;
	}
	
	/**
	 * Returns the number of parameters
	 * @param inputString
	 * @return
	 */
	public static int getNumberOfParameters(String inputString) {
		String[] split = inputString.split(" ");
		if (split.length >= 2) {
			return split.length - 1;
		}
		return 0;
	}
	
	/**
	 * Returns a single parameter
	 * @param parameters
	 * @param p
	 * @return
	 */
	public static String getSingleParameter(String parameters, Integer p) {
		String[] split = parameters.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (i == p) {
				return split[i].trim();
			}
		}
		return null;
	}
	
	/**
	 * Formats a string to java.time.LocalDateTime
	 * @param time
	 * @return
	 */
	public static LocalDateTime formatTimeString(String time) {
		try {
			LocalDateTime aLD = LocalDateTime.parse(time);
			return aLD;
		} catch (Exception ex) {
			time += "T00:00:00";
			LocalDateTime aLD = LocalDateTime.parse(time);
			return aLD;
		}
		
	}
}
