package in.ineuron.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class BlobInserionApp {

	public static void main(String[] args) {
		//Resources used
		Connection connection =null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		//Variables used 
		String name = null;
		String imageLoc=null;
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			
			String sqlInsertQuery="insert into persons(`name`,`image`) values(?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if (pstmt != null) {
				scanner = new Scanner(System.in);

				// collecting the inputs
				if (scanner != null) {
					System.out.print("Enter the username :: ");
					name = scanner.next();

					System.out.print("Enter the image location :: ");
					imageLoc = scanner.next();

				}

				// Set the input values to Query
				pstmt.setString(1, name);
				pstmt.setBinaryStream(2, new FileInputStream(new File(imageLoc)));

				// execute the query
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows inserted is :: " + rowAffected);

			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scanner.close();
		}
	}

}
