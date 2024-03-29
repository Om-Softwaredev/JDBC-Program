package in.ineuron.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	private JdbcUtil() {
		
	}
	
	// Load and register the driver
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public static Connection getJDBCConnection() throws SQLException, IOException {
		// Take the data from properties file
				FileInputStream fis = new FileInputStream("E:\\om\\FULL STACK JAVA DEVELOPMENT\\AdvanceJavaProgram\\JDBC\\03-JDBC-StandardApp\\application.properties");
				Properties properties = new Properties();
				properties.load(fis);

				// Step2. Establish the Connection
				Connection connection = DriverManager.getConnection(properties.getProperty("url"),
						properties.getProperty("username"), properties.getProperty("password"));
				System.out.println("connection object created...");
				return connection;
	}
	
	public static void cleanUp(Connection con,Statement statement,ResultSet resultSet) throws SQLException {
		//Step-06 Close the resources
		if (con != null) {
			con.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
		
	}
}
