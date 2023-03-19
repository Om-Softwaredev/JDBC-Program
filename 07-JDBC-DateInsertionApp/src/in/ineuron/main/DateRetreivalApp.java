package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import in.ineuron.util.JdbcUtil;

public class DateRetreivalApp {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int id=1;
		
		try {
			connection = JdbcUtil.getJDBCConnection();
			
			String sqlSelectQuery ="select id,name,dob,dom from users where id =?";
			if (connection != null) 
				pstmt=connection.prepareStatement(sqlSelectQuery);
			if (pstmt != null) {
				// setting input values
				pstmt.setInt(1, id);

				// executing the query
				resultSet = pstmt.executeQuery();
			}
			if (resultSet.next()) {
				System.out.println("ID\tNAME\tDOB\t\tDOM");
				int sid = resultSet.getInt(1);
				String name = resultSet.getString(2);
//				Date sdob = resultSet.getDate(3);
//				Date sdom = resultSet.getDate(4);
//				System.out.println(sid+"\t"+name+"\t"+sdob+"\t"+sdom);
				
				java.sql.Date sdob = resultSet.getDate(3);
				java.sql.Date sdom = resultSet.getDate(4);
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				String strDob=sdf.format(sdob);
				String strDom= sdf.format(sdom);
				
				System.out.println(sid+"\t"+name+"\t"+strDob+"\t"+strDom);
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
