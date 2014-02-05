/**
 * 
 */
package com.expense.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @author jitender.saini
 * 
 */
public class AppUtils {
	public static String encodePasswordToMD5(String password) {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		return passwordEncoder.encodePassword(password, null);
	}

	public static long getDateDifferenceAsDays(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateMidnight start = new DateMidnight(df.format(new Date()));
		DateMidnight end = new DateMidnight(df.format(date));
		return Days.daysBetween(start, end).getDays();
	}

	public static Date getReducedDaysFromCurrentDate(int reducedDays) {
		int x = -reducedDays;
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, x);
		return cal.getTime();
	}

	public static String getFormatedDate(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static Date getReducedDaysFromGivenDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -days);
		return cal.getTime();

	}
	
	public static Date convertCurrentDateToCurrentTimeZone() {
		Date date = new Date();  
		   
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");  
		/*formatter.setTimeZone(TimeZone.getTimeZone("UTC"));  
		   
		// Prints the date in the CET timezone  
		System.out.println(formatter.format(date));*/  
		   
		// Set the formatter to use a different timezone  
		formatter.setTimeZone(TimeZone.getDefault());  
		   
		// Prints the date in the IST timezone  
		
		try {
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static Date convertGivenDateToCurrentTimeZone(Date date) {
		//Date date = new Date();  
		   
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");  
		/*formatter.setTimeZone(TimeZone.getTimeZone("UTC"));  
		   
		// Prints the date in the CET timezone  
		System.out.println(formatter.format(date));*/  
		   
		// Set the formatter to use a different timezone  
		formatter.setTimeZone(TimeZone.getDefault());  
		   
		// Prints the date in the IST timezone  
		
		try {
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static Date convertCurrentDateToGivenTimeZone(String timezone) {
		//Date date = new Date();  
		   
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");  
		/*formatter.setTimeZone(TimeZone.getTimeZone("UTC"));  
		   
		// Prints the date in the CET timezone  
		System.out.println(formatter.format(date));*/  
		   
		// Set the formatter to use a different timezone  
		formatter.setTimeZone(TimeZone.getTimeZone(timezone));  
		   
		// Prints the date in the IST timezone  
		
		try {
			System.out.println("The date is: "+formatter.parse(formatter.format(new Date())));
			return formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static DateTime getDT() {
		DateTime dt = new DateTime();
	    DateTime dtLondon = dt.withZone(DateTimeZone.forID("Europe/London"));
	    System.out.println("dtLondon::::: "+dtLondon);
	    return dtLondon;
	}
}
