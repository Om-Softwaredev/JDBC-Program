package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class ScrollableApp {

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
				stmt=connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_UPDATABLE);
			
			String sqlSelectQuery="select eid,ename,eage,eaddr from employee";
			if (stmt != null) 
				resultSet=stmt.executeQuery(sqlSelectQuery);
			
			System.out.println("Moving in FORWARD Direction.....");
			if (resultSet != null) {
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				}
				
				System.out.println();
				
				System.out.println("Moving in BACKWARD Direction.....");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (resultSet.previous()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				}
				
				System.out.println();
				
				resultSet.first();//cursor will be moved to first record
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				resultSet.last();//cursor will be moved to last record
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				resultSet.first();//take cursor to first record
				
				resultSet.absolute(3);//from the beginning of the RESULTSET
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				resultSet.relative(2);//from the current position move downwards
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				resultSet.first();//take cursor to first record
				resultSet.absolute(2);//from the beginning of the RESULTSET
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				resultSet.relative(-1);
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				resultSet.absolute(-1);
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
				
				resultSet.absolute(5); // from the BFR take the cursor to 5th position
				resultSet.relative(-2);// from the current cursor position take the cursor 2 steps in backward direction
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
						+ "\t" + resultSet.getString(4));

				resultSet.relative(5);// from the current cursor position take the cursor 5 steps in forward direction
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
						+ "\t" + resultSet.getString(4));

				resultSet.absolute(-5);// from the ALR move the cursor in backward direction
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
						+ "\t" + resultSet.getString(4));

				resultSet.last();//take the cursor to last row
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
						+ "\t" + resultSet.getString(4));

				resultSet.relative(-1);//from the current cursor position move the cursor 1step in backward direction
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
						+ "\t" + resultSet.getString(4));
				
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
