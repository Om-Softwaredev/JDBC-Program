
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
public class UpdateApp {

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt=null;
			Scanner scanner = null;
			
		try {
			connection = JdbcUtil.getJDBCConnection();
			System.out.println("connection established...");
			String sqlUpdateQuery = "update student1 set saddress=? where sid=?";
			
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			
			if (pstmt != null) {
				scanner = new Scanner(System.in);
				System.out.println("Enter student id : ");
				int sid=scanner.nextInt();
				
				System.out.print("Enter the student address : ");
				String saddress = scanner.next();
				
				//use precompiled query to set the values
				pstmt.setString(1, saddress);
				pstmt.setInt(2, sid);
				
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

