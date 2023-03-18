
package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;
/**
 * @author Omprakash
 * @Company iNeuron
 * @see www.ineuron.ai
 *
 */
public class InsertAppDynamicInput {

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt=null;
			Scanner scanner = null;
			
		try {
			connection = JdbcUtil.getJDBCConnection();
			System.out.println("connection established...");
			String sqlInsertQuery = "insert into student1(`sname`,`sage`,`saddress`,`sgender`)values(?,?,?,?)";
			
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if (pstmt != null) {
				
				scanner = new Scanner(System.in);
				System.out.print("Enter the student name : ");
				String sname = scanner.next();
				
				System.out.print("Enter the student age : ");
				int sage = scanner.nextInt();
				
				System.out.print("Enter the student address : ");
				String saddress = scanner.next();
				
				System.out.print("Enter the student gender : ");
				String sgender = scanner.next();
				
				//use precompiled query to set the values
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);
				pstmt.setString(4, sgender);
				
				System.out.println(sqlInsertQuery);
				// execute the query
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of row Affected : "+rowCount);
			}
			
			
		}catch (IOException ie) {
			ie.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtil.cleanUp(connection,pstmt,null);
				scanner.close();
				System.out.println("Closing the resources....");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

