package in.ineuron.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class DeleteApp {

	public static void main(String[] args) throws SQLException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();//Same as ResultSet(connected), But it is scrollable and updatable 
		
		//Setting url.username,password
		crs.setUrl("jdbc:mysql:///octbatch?relaxAutoCommit=true");
		crs.setUsername("root");
		crs.setPassword("root");
		
		//setting a command for execution
		crs.setCommand("select id,name,age,address,salary from student");
		crs.execute();
		
		while (crs.next()) {
			int actualSalary=crs.getInt(5);
			if (actualSalary<5000) {
				crs.deleteRow();
			}
		}
		System.out.println("Record Deleted Succefully...");
		crs.moveToCurrentRow();
		crs.acceptChanges();
		crs.close();
		
	}

}
