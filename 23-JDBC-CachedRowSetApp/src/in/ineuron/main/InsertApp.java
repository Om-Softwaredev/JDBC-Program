package in.ineuron.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class InsertApp {

	public static void main(String[] args) throws SQLException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();//Same as ResultSet(connected), But it is scrollable and updatable 
		
		//Setting url.username,password
		crs.setUrl("jdbc:mysql:///octbatch");
		crs.setUsername("root");
		crs.setPassword("root");
		
		//setting a command for execution
		crs.setCommand("select id,name,age,address,salary from student");
		crs.execute();
		
		Scanner scanner = new Scanner(System.in);
		crs.moveToInsertRow();
		while (true) {
			
			System.out.print("Enter the id:: ");
			Integer id= scanner.nextInt();
			
			System.out.print("Enter the name:: ");
			String name= scanner.next();
			
			System.out.print("Enter the age:: ");
			Integer age= scanner.nextInt();
			
			System.out.print("Enter the address:: ");
			String address= scanner.next();
			
			System.out.print("Enter the salary:: ");
			Integer salary= scanner.nextInt();
			
			crs.updateInt(1, id);
			crs.updateString(2, name);
			crs.updateInt(3, age);
			crs.updateString(4, address);
			crs.updateInt(5, salary);
			
			crs.insertRow();
			
			System.out.println("Record Inserted Succefully....");
			System.out.print("Do you want to insert one more record [YES/NO]: ");
			String option=scanner.next();
			if (option.equalsIgnoreCase("no")) {
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (crs.next()) {
					System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4));
					
				}
				break;
			} 
			
			crs.moveToCurrentRow();
			crs.acceptChanges();//even if the connection gets disconnected the record should be added
		}
		scanner.close();
		crs.close();
		
	}

}
