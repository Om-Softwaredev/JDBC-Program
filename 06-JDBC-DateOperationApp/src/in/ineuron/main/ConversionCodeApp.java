package in.ineuron.main;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConversionCodeApp {

	public static void main(String[] args) throws Exception {

		//Read the input from the user
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the date:: (dd-MM-yyyy)");
		String sdate = scanner.next();
		
		//convert the date from String format to java.util.date
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDate= sdf.parse(sdate);
		
		// Convert java.util.date to java.sql.date
		long value = uDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(value);
		
		//Printing all the 3 formats of date
		System.out.println("String format date is : "+sdate);
		System.out.println("Util date is : "+uDate);
		System.out.println("SqlDate is :"+sqlDate);
		
		System.out.println();
		
		System.out.print("Enter the dom in the following format::(yyyy-MM-dd)");
		String standardInput= scanner.next();
		java.sql.Date sqlStandardInputDate = java.sql.Date.valueOf(standardInput);
		System.out.println("String StandardInput : "+standardInput);
		System.out.println("String sqlStandardInputDate : "+sqlStandardInputDate);
		scanner.close();
	}

}
