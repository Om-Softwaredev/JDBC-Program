package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

//persistence logic using JDBC api
public class StudentDaoImpl implements IStudentDao {

	Connection connection= null;
	PreparedStatement pstmt=null;
	ResultSet resultSet =null;
	
	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		
		String sqlInsertQuery ="insert into student(`name`,`age`,`address`)values(?,?,?)";
		try {
			connection=JdbcUtil.getJDBCConnection();
			if (connection != null) 
				pstmt=connection.prepareStatement(sqlInsertQuery);
			if (pstmt != null) {
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);
				
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected==1) {
					return "success";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		String sqlSelectQuery ="select id,name,age,address from student where id=?";
		Student student=null;
		try {
			connection=JdbcUtil.getJDBCConnection();
			if (connection != null) 
				pstmt=connection.prepareStatement(sqlSelectQuery);
			
			if (pstmt != null) 
				pstmt.setInt(1, sid);
			
			if (pstmt != null) 
				resultSet = pstmt.executeQuery();
			
			if (resultSet != null) {
				if (resultSet.next()) {
					student = new Student();
					
					//copy resultSet data to Student object
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddress(resultSet.getString(4));
					return student;
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return student;
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		return null;
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqldeleteQuery ="delete from student where id=? ";
		try {
			connection=JdbcUtil.getJDBCConnection();
			if (connection != null) 
				pstmt=connection.prepareStatement(sqldeleteQuery);
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected==1) {
					return "success";
				}else {
					return "not found";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		
		return "failure";
	}

}
