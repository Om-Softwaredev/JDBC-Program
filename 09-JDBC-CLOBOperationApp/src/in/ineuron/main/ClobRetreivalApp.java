package in.ineuron.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

import in.ineuron.util.JdbcUtil;

public class ClobRetreivalApp {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int id=2;
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			
			String sqlSelectQuery ="select id,name,history from cities where id =?";
			if (connection != null) 
				pstmt=connection.prepareStatement(sqlSelectQuery);
			if (pstmt != null) {
				// setting input values
				pstmt.setInt(1, id);

				// executing the query
				resultSet = pstmt.executeQuery();
			}
			if (resultSet.next()) {
				System.out.println("ID\tNAME\tHISTORY");
				int pid = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Reader reader = resultSet.getCharacterStream(3);
				
				File file = new File("History_copied.txt");
				FileWriter writer = new FileWriter(file);
				
				
				IOUtils.copy(reader, writer);
				reader.close();
				writer.close();
				System.out.println(pid+"\t"+name+"\t"+file.getAbsolutePath());
			}else {
				System.out.println("Record not found for given id : "+id);
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, resultSet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
