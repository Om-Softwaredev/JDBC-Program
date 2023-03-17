package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteApp {

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
		
		//Step-04 execute the query and process the resultSet
		String sqlDeleteQuery ="delete from student1 where sid =2";
		int rowCount = statement.executeUpdate(sqlDeleteQuery);
		System.out.println("No. of row Affected : "+rowCount);
		
		
		//Step-05 Handle SQLException if generated
		// we have handle the exception by throws
		
		//Step-06 Close the resources
		statement.close();
		connection.close();
		System.out.println("Closing the resources....");
	}
}
