package com.ll.iod.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarDateHandler {

	private CalendarDateHandler() {
		// prevent instantiation from outside the class
	}

	/**
	 * This method formats the current date into given date format. For example, if
	 * user wants to convert the current date into MMddYYYYHHmm, the format would be
	 * passed as a parameter and this method will get it converted into string value
	 * in expected format <code>022620221036</code> which is equivalent to 26th Feb
	 * 2022 at 10 AM 36 Minutes
	 *
	 * @return String current into string format.
	 */
	public static String getFormatedDate(String format) {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(format);
		String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}
}
