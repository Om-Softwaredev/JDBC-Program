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
		JdbcRowSet jrs = rsf.createJdbcRowSet();//Same as ResultSet(connected), But it is scrollable and updatable 
		
		//Setting url.username,password
		jrs.setUrl("jdbc:mysql:///octbatch");
		jrs.setUsername("root");
		jrs.setPassword("root");
		
		//setting a command for execution
		jrs.setCommand("select id,name,age,address from student");
		jrs.execute();
		
		System.out.println("Retrieving the records in Forward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while (jrs.next()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
			
		}
		System.out.println();
		
		System.out.println("Retrieving the records in Backward direction....");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while (jrs.previous()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
			
		}
		jrs.beforeFirst();
		System.out.println();
		jrs.absolute(3);
		System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		
		jrs.absolute(-1);
		System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		
		jrs.relative(-2);
		System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
	}

}
