package in.ineuron.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class TestApp {

	public static void main(String[] args) throws SQLException {

		//create an Object of a class which implements javax.sql.DataSource
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setUrl("jdbc:mysql:///octbatch");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		
		//Getting the connection object from connection pool
		Connection connection = dataSource.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select eid,ename,eage,eaddr,salary from employee");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getInt(5));
			
		}
		
		//Sending the connection object back to connection pool
		connection.close();
	}

}
