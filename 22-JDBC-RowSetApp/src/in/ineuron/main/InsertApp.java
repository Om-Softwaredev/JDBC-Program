package in.ineuron.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class InsertApp {

	public static void main(String[] args) throws SQLException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();//Same as ResultSet(connected), But it is scrollable and updatable 
		
		//Setting url.username,password
		jrs.setUrl("jdbc:mysql:///octbatch");
		jrs.setUsername("root");
		jrs.setPassword("root");
		
		//setting a command for execution
		jrs.setCommand("select id,name,age,address from student");
		jrs.execute();
		
		Scanner scanner = new Scanner(System.in);
		jrs.moveToInsertRow();
		while (true) {
			System.out.print("Enter the name:: ");
			String name= scanner.next();
			
			System.out.print("Enter the age:: ");
			Integer age= scanner.nextInt();
			
			System.out.print("Enter the address:: ");
			String address= scanner.next();
			
			jrs.updateString(2, name);
			jrs.updateInt(3, age);
			jrs.updateString(4, address);
			
			jrs.insertRow();
			System.out.println("Record Inserted Succefully....");
			System.out.print("Do you want to insert one more record [YES/NO]: ");
			String option=scanner.next();
			if (option.equalsIgnoreCase("no")) {
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (jrs.next()) {
					System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
					
				}
				break;
			} 
		}
		scanner.close();
		jrs.close();
		
	}

}
