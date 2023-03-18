
package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;
/**
 * @author Omprakash
 * @Company iNeuron
 * @see www.ineuron.ai
 *
 */
public class SelectApp {

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			Connection connection = null;
			PreparedStatement pstmt=null;
			Scanner scanner = null;
			ResultSet resultSet =null;
			int sid=0;
			
		try {
			connection = JdbcUtil.getJDBCConnection();
			System.out.println("connection established...");
			String sqlSelectQuery = "select sid,sname,sage,saddress,sgender from student1 where sid=?";
			
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlSelectQuery);
			
			if (pstmt != null) {
				
				scanner = new Scanner(System.in);
				System.out.println("Enter student id : ");
				sid=scanner.nextInt();
				//use precompiled query to set the values
				pstmt.setInt(1, sid);
				
				
				System.out.println(sqlSelectQuery);
				// execute the query
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {
				if (resultSet.next()) {
					System.out.println("SID\tSNAME\tSAGE\tSADDRESS\tSGENDER");
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5));
				}else {
					System.out.println("record not found for the Given id "+sid);
				}
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
				JdbcUtil.cleanUp(connection,pstmt,resultSet);
				scanner.close();
				System.out.println("Closing the resources....");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

