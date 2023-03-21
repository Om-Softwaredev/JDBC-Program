package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class ClobInserionApp {

	public static void main(String[] args) {
		//Resources used
		Connection connection =null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			
			String sqlInsertQuery="insert into employee(`ename`,`eage`,`eaddr`)values (?,?,?)";
			if (connection != null) 
				pstmt=connection.prepareStatement(sqlInsertQuery);
			
			if (pstmt != null) {
				scanner= new Scanner(System.in);
				while(true) {
					System.out.print("Enter name : ");
					String name=scanner.next();
					System.out.print("Enter age : ");
					int age=scanner.nextInt();
					System.out.print("Enter address : ");
					String address=scanner.next();
					
					pstmt.setString(1, name);
					pstmt.setInt(2, age);
					pstmt.setString(3, address);
					
					//Query added to batch file 
					pstmt.addBatch();
					
					System.out.print("Do you want to insert one more record[YES/NO]: ");
					String option=scanner.next();
					
					if(option.equalsIgnoreCase("no")) {
						break;
					}
					
				}
				
				// executing the queries present inside batch file
				pstmt.executeBatch();
				System.out.println("record inserted successfully.....");
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
