package in.ineuron.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class DeleteApp {

	public static void main(String[] args) throws SQLException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();//Same as ResultSet(connected), But it is scrollable and updatable 
		
		//Setting url.username,password
		jrs.setUrl("jdbc:mysql:///octbatch");
		jrs.setUsername("root");
		jrs.setPassword("root");
		
		//setting a command for execution
		jrs.setCommand("select id,name,age,address,salary from student");
		jrs.execute();
		
		while (jrs.next()) {
			int actualSalary=jrs.getInt(5);
			if (actualSalary<5000) {
				jrs.deleteRow();
			}
		}
		System.out.println("Record Deleted Succefully...");
		
		jrs.close();
		
	}

}
