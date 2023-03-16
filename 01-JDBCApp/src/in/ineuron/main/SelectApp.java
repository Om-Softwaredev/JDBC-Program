package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		//Step-01 Load and register the Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Loading the Driver....");
		
		//Step-02 Establish the connection
		String url ="jdbc:mysql://localhost:3306/octbatch";
		String user ="root";
		String password = "root";
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println("Connection object created.....");
		
		//Step-03 create statement object and send the query
		Statement statement = connection.createStatement();
		System.out.println("Statement object created.....");
		
		//Step-04 execute the query and process the resultSet
		String sqlSelectQuery ="select sid,sname,sage,saddress from student1";
		ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
		System.out.println("ResultSet object created.....");
		
		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
		while (resultSet.next()) {
			int sid = resultSet.getInt(1);
			String sname=resultSet.getString(2);
			int sage = resultSet.getInt(3);
			String saddress=resultSet.getString(4);
			System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddress);
		}
		
		//Step-05 Handle SQLException if generated
		// we have handle the exception by throws
		
		//Step-06 Close the resources
		resultSet.close();
		statement.close();
		connection.close();
		
	}

}
