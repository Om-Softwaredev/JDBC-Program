package in.ineuron.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestApp {

	public static void main(String[] args) throws SQLException {

		String configFile="src\\in\\ineuron\\main\\db.properties";
		HikariConfig config=new HikariConfig(configFile);
		
		try (HikariDataSource dataSource = new HikariDataSource(config)) {
			//Getting the connection object from connection pool
			Connection connection = dataSource.getConnection();
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select eid,ename,eage,eaddr,salary from employee");
			System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getInt(5));
				
			}
		}
	}

}
