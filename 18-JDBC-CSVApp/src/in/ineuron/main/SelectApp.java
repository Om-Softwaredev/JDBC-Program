package in.ineuron.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectApp {
	
	// csv filename represents the table name
	private static final String CSV_FILE = "select * from data.csv";

	public static void main(String[] args) {
		
		//jdbc:Text:location where the file is present
		String url="jdbc:Text:///E:\\om\\images";
		try(Connection connection=DriverManager.getConnection(url)) {
			try(PreparedStatement pstmt=connection.prepareStatement(CSV_FILE)){
				try(ResultSet resultSet=pstmt.executeQuery()){
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
					}
				}
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
