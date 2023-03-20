package in.ineuron.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

import in.ineuron.util.JdbcUtil;

public class ImageRetreivalApp {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int id=1;
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			
			String sqlSelectQuery ="select id,name,image from persons where id =?";
			if (connection != null) 
				pstmt=connection.prepareStatement(sqlSelectQuery);
			if (pstmt != null) {
				// setting input values
				pstmt.setInt(1, id);

				// executing the query
				resultSet = pstmt.executeQuery();
			}
			if (resultSet.next()) {
				System.out.println("ID\tNAME\tIMAGE");
				int pid = resultSet.getInt(1);
				String name = resultSet.getString(2);
				InputStream is= resultSet.getBinaryStream(3);
				
				File file = new File("copied.jpg");
				FileOutputStream fos = new FileOutputStream(file);
				
				//performance low
//				int i = is.read();
//				while (i !=-1) {
//					fos.write(i);
//					i = is.read();
//				}
				
				//performance is high, but difficult for programmer
//				byte[] b=new byte[1024];
//				//Reads some number of bytes from the input stream and stores them intothe buffer array b.
//				//If the length of b is zero, then no bytes are read and 0 is returned
//				while (is.read(b)>0) {
//					fos.write(b);
//				}
				
				//Using jar:-commons-io-2.8.0.jar
				//copying the data from is to fos
				IOUtils.copy(is, fos);
				fos.close();
				System.out.println(pid+"\t"+name+"\t"+file.getAbsolutePath());
			}else {
				System.out.println("Record notfound for given id : "+id);
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
