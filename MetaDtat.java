package Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.DatabaseMetaData;


public class MetaDtat {
	public static void main(String args[])throws Exception{   
		int t=0;
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		int a=930;
		  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		long uq=930;
		String s="Vamishi";
		  
		PreparedStatement ps=con.prepareStatement("SELECT Name FROM myguests WHERE Unique_Id=? AND Book_author=?");
		
		ps.setLong(1, uq);
		ps.setString(2, s);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			PreparedStatement cs=con.prepareStatement("insert into purchase values(?,?,?)");
			cs.setString(1, "30");
			cs.setString(2, "4asjd");
			cs.setString(3, "hdhdfh");
			t=cs.executeUpdate();
		}
		else
			System.out.print("Unknown");
		 
		}
		
	}


