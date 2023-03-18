
package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.ineuron.util.JdbcUtil;
/**
 * @author Omprakash
 * @Company iNeuron
 * @see www.ineuron.ai
 *
 */
public class InsertApp {

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt=null;
			
		try {
			connection = JdbcUtil.getJDBCConnection();
			System.out.println("connection established...");
			String sqlInsertQuery = "insert into student1(`sname`,`sage`,`saddress`,`sgender`)values(?,?,?,?)";
			
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if (pstmt != null) {
				//use precompiled query to set the values
				pstmt.setString(1, "lalitha");
				pstmt.setInt(2, 26);
				pstmt.setString(3, "USA");
				pstmt.setString(4, "F");
				
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
				System.out.println("Closing the resources....");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

