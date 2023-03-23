package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class TestApp {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql:///octbatch", "root", "root");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id,name,age,address,salary from student");
		
		RowSetFactory rsf=RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();//Disconnected RowSet
		crs.populate(resultSet);
		
		connection.close();//Operation not allowed after ResultSet Closed
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while (crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
	}

}
