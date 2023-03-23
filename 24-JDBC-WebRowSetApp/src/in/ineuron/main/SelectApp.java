package in.ineuron.main;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;


public class SelectApp {

	public static void main(String[] args) throws SQLException, Exception {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		WebRowSet wrs = rsf.createWebRowSet();//Same as ResultSet(connected), But it is scrollable and updatable 
		
		//Setting url.username,password
		wrs.setUrl("jdbc:mysql:///octbatch");
		wrs.setUsername("root");
		wrs.setPassword("root");
		
		//setting a command for execution
		wrs.setCommand("select id,name,age,address,salary from student");
		wrs.execute();
		
		FileWriter fw = new FileWriter("std.xml");
		wrs.writeXml(fw);
		System.out.println("Student data Transfered to std.xml file....");
		fw.close();
		
	}

}
