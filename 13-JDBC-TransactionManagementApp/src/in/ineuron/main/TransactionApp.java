package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class TransactionApp {

	public static void main(String[] args) {
		//Resources used
		Connection connection =null;
		Statement stmt = null;
		ResultSet resultSet=null;
		Scanner scanner=null;
	
		try {
			connection = JdbcUtil.getJDBCConnection();
			if (connection != null) 
				stmt=connection.createStatement();
			if (stmt != null) 
				resultSet=stmt.executeQuery("select name,balance from accounts");
			System.out.println("Data before Transaction....");
			if (resultSet != null) {
				System.out.println("NAME\tBALANCE");
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2));
				}
			}
			
			System.out.println("\nTransaction begins......");
			connection.setAutoCommit(false);

			//prepare the operations as single unit
			stmt.executeUpdate("update accounts set balance = balance-5000 where name='sachin'");
			stmt.executeUpdate("update accounts set balance = balance+5000 where name='dhoni'");
			System.out.print("please confirm Transaction of 5000INR [YES/NO]: ");
			scanner=new Scanner(System.in);
			String options=scanner.next();
			
			if (options.equalsIgnoreCase("yes")) {
				connection.commit();
			} else {
				connection.rollback();
			}
			System.out.println("\nData After Transaction....");
			
			ResultSet rs=stmt.executeQuery("select name,balance from accounts");
			if (rs != null) {
				System.out.println("NAME\tBALANCE");
				while (rs.next()) {
					System.out.println(rs.getString(1)+"\t"+rs.getInt(2));
				}
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, stmt,resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
	}

}
