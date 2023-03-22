package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class SesitiveNatureApp {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		//Resources used
		Connection connection =null;
		Statement stmt = null;
		ResultSet resultSet=null;
		
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			
			if (connection != null) 
				//expecting RESULTSET to be SCROLLABLE and UPDATABLE
				stmt=connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_UPDATABLE);
			
			String sqlSelectQuery="select eid,ename,eage,eaddr from employee";
			if (stmt != null) 
				resultSet=stmt.executeQuery(sqlSelectQuery);
			
			System.out.println("Records before Updation.....");
			if (resultSet != null) {
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				}
				System.out.println();
				
				System.out.println("Application is in Pausing State, please Update the database....");
				System.in.read();
				System.out.println("Records after Updation....");
				
				
				resultSet.beforeFirst();//Takes the cursor to BFR
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (resultSet.next()) {
					resultSet.refreshRow();
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
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
	}

}
