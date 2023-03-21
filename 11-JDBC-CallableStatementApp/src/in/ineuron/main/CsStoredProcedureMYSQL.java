package in.ineuron.main;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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
    
    PROCEDURE `octbatch`.`P_GET_PRODUCT_DETAILS_BY_ID`(IN id INT, OUT NAME VARCHAR(20),OUT rate INT,OUT qnt INT)
    
	BEGIN
		SELECT pname,price,qty INTO NAME,rate,qnt FROM products WHERE pid=id;
	END$$

DELIMITER ;
 *
 */

public class CsStoredProcedureMYSQL {

	private static final String storedProcedureCall ="{CALL P_GET_PRODUCT_DETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) {
		//Resources used
		Connection connection =null;
		CallableStatement cstmt = null;
		Scanner scanner = null;
		
		Integer id = null;
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			if (connection != null) 
				cstmt = connection.prepareCall(storedProcedureCall);
			
			scanner = new Scanner(System.in);
			if (scanner != null) {
				System.out.print("Enter the id : ");
				id = scanner.nextInt();
			}
			//Setting the input values
			if (cstmt != null) {
				cstmt.setInt(1, id);
			}
			//Setting the dataTypes of output values
			if (cstmt != null) {
				cstmt.registerOutParameter(2, Types.VARCHAR);
				cstmt.registerOutParameter(3, Types.INTEGER);
				cstmt.registerOutParameter(4, Types.INTEGER);
			}
			
			// Run the Stored Procedure
			if (cstmt != null) {
				cstmt.execute();
			}
			
			//Retrieve the result
			if (cstmt != null) {
				System.out.println("Product Name is : "+cstmt.getString(2));
				System.out.println("Product Cost is : "+cstmt.getInt(3));
				System.out.println("Product Qty is : "+cstmt.getInt(4));
			}
			
			
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, cstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
