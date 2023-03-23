package in.ineuron.main;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;


public class SelectApp {

	public static void main(String[] args) throws SQLException {
		
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		CachedRowSet crs = rsf.createCachedRowSet();//Same as ResultSet(connected), But it is scrollable and updatable 
		
		//Setting url.username,password
		crs.setUrl("jdbc:mysql:///octbatch");
		crs.setUsername("root");
		crs.setPassword("root");
		
		//setting a command for execution
		crs.setCommand("select id,name,age,address,salary from student");
		crs.execute();
		
		System.out.println("Retrieving the records in Forward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS\tSALARY");
		while (crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
			
		}
		System.out.println();
		
		System.out.println("Retrieving the records in Backward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while (crs.previous()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
			
		}
		crs.beforeFirst();
		System.out.println();
		crs.absolute(3);
		System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		
	}

}
