package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.hxtt.sql.cs;

public class InsertApp {
	
	// workbook.<sheetname> represents the table name
	private static final String EXCEL_INSERT = "insert into data.student values(?,?,?)";

	public static void main(String[] args) {
		Scanner scanner=null;
		String name = null;
		Integer id = null;
		String address= null;
		
		try {
			scanner = new Scanner(System.in);
			if (scanner != null) {
				
				System.out.print("Enter the id: ");
				id=scanner.nextInt();
				System.out.print("Enter the name: ");
				name=scanner.next();
				System.out.print("Enter the address: ");
				address=scanner.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		//jdbc:excel:location where the file is present
		String url="jdbc:excel:///E:\\om\\images";
		try(Connection connection=DriverManager.getConnection(url)) {
			try(PreparedStatement pstmt=connection.prepareStatement(EXCEL_INSERT)){
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, address);
				
				int countRow = pstmt.executeUpdate();
				
				if (countRow==0) {
					System.out.println("Record not iserted...");
				} else {
					System.out.println("Record iserted sucessfully...");
				}
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
