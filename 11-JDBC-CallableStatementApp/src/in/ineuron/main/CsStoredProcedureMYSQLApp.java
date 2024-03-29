package in.ineuron.main;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

/**
 * 
 * @author Omprakash
 *
 */

/**
 * StoredProcedure
 * 
DELIMITER $$

CREATE
    
    PROCEDURE `octbatch`.`P_GET_PRODUCT_BY_NAME`(IN name1 VARCHAR(20),IN name2 VARCHAR(20))
    
	BEGIN
		SELECT pid,pname,price,qty FROM products WHERE pname IN(name1,name2);
	END$$

DELIMITER ;
 *
 */

public class CsStoredProcedureMYSQLApp {

	private static final String storedProcedureCall ="{CALL P_GET_PRODUCT_BY_NAME(?,?)}";
	public static void main(String[] args) {
		//Resources used
		Connection connection =null;
		CallableStatement cstmt = null;
		ResultSet resultSet=null;
		Scanner scanner = null;
		
		String prod1=null;
		String prod2=null;
		boolean flag=false;
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			if (connection != null) 
				cstmt = connection.prepareCall(storedProcedureCall);
			
			scanner = new Scanner(System.in);
			if (scanner != null) {
				System.out.print("Enter name1 : ");
				prod1 = scanner.next();
				System.out.print("Enter name2 : ");
				prod2 = scanner.next();
			}
			//Setting the input values
			if (cstmt != null) {
				cstmt.setString(1, prod1);
				cstmt.setString(2, prod2);
			}
		
			// Run the Stored Procedure
			if (cstmt != null) {
				cstmt.execute();
			}
			
			//Retrieve the result
			if (cstmt != null) {
				resultSet = cstmt.getResultSet();
			}
			if (resultSet != null) {
				System.out.println("PID\tPNAME\tPCOST\tQTY");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getInt(4));
					flag =true;
				}
			}
			//Displaying the nature of the result
			if (flag) {
				System.out.println("Record available and displayed...");
			} else {
				System.out.println("Record not available....");
			}
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, cstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
