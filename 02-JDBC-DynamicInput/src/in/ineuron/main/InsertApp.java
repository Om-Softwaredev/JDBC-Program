
package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

	
		
		//Step-02 Establish the connection
		String url ="jdbc:mysql:///octbatch";
		String user ="root";
		String password = "root";
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println("Connection object created.....");
		
		//Step-03 create statement object and send the query
		Statement statement = connection.createStatement();
		System.out.println("Statement object created.....");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the student name : ");
		String sname = scanner.next();
		
		System.out.print("Enter the student age : ");
		int sage = scanner.nextInt();
		
		System.out.print("Enter the student address : ");
		String saddress = scanner.next();
		
		System.out.print("Enter the student gender : ");
		String sgender = scanner.next();
		
		//Step-04 execute the query and process the resultSet
		// 1st Approach
//		String sqlInsertQuery ="insert into student1 (`sname`,`sage`,`saddress`) values('"+sname+"',"+sage+",'"+saddress+"')";
//		System.out.println(sqlInsertQuery);
//		int rowCount = statement.executeUpdate(sqlInsertQuery);
//		System.out.println("No. of row Affected : "+rowCount);
		
		// 2nd Approach
//		sname="'"+sname+"'";
//		saddress="'"+saddress+"'";
//		String sqlInsertQuery ="insert into student1 (`sname`,`sage`,`saddress`) values("+sname+","+sage+","+saddress+")";
//		System.out.println(sqlInsertQuery);
//		int rowCount = statement.executeUpdate(sqlInsertQuery);
//		System.out.println("No. of row Affected : "+rowCount);
		
		// 3rd Approach(recommended)
		String sqlInsertQuery =String.format("insert into student1 (`sname`,`sage`,`saddress`,`sgender`) values('%s',%d,'%s','%s')",sname,sage,saddress,sgender);
		System.out.println(sqlInsertQuery);
		int rowCount = statement.executeUpdate(sqlInsertQuery);
		System.out.println("No. of row Affected : "+rowCount);
		
		
		//Step-05 Handle SQLException if generated
		// we have handle the exception by throws
		
		//Step-06 Close the resources
		statement.close();
		connection.close();
		scanner.close();
		System.out.println("Closing the resources....");
	}
}

