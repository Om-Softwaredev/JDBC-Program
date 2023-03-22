package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class UpdateRecordApp {

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
			
			String sqlSelectQuery="select eid,ename,eage,eaddr,salary from employee";
			if (stmt != null) 
				resultSet=stmt.executeQuery(sqlSelectQuery);
			
			System.out.println("Records before Updation.....");
			if (resultSet != null) {
				System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getInt(5));
				}
				
				resultSet.beforeFirst();
				while (resultSet.next()) {
					int originalSalary = resultSet.getInt(5);
					if (originalSalary<5000) {
						int incrSalary=originalSalary+1000;
						resultSet.updateInt(5, incrSalary);
						resultSet.updateRow();
					}
			
				}
				
			
				System.out.println();
				System.out.println("Records After Updation.....");
				resultSet.beforeFirst();//taking cursor to BFR
				if (resultSet != null) {
					System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getInt(5));
					}
				
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
