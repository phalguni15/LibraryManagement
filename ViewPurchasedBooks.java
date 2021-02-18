package Project;

import java.awt.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewPurchasedBooks extends JFrame {
	private JPanel contentPane;
	private JTable table;
	/**
	 * Create the frame.
	 */
	 ViewPurchasedBooks() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String data[][]=null;
		String column[]=null;
		try{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			PreparedStatement ps=con.prepareStatement("select * from purchase",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=ps.executeQuery();
			
			ResultSetMetaData rsmd=rs.getMetaData();
			int cols=rsmd.getColumnCount();
			column=new String[cols];
			for(int i=1;i<=cols;i++){
				column[i-1]=rsmd.getColumnName(i);
			}
			
			rs.last();
			int rows=rs.getRow();
			rs.beforeFirst();

			data=new String[rows][cols];
			int count=0;
			while(rs.next()){
				for(int i=1;i<=cols;i++){
					data[count][i-1]=rs.getString(i);
				}
				count++;
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		
		table = new JTable(data,column);
		JScrollPane sp=new JScrollPane(table);
		
		contentPane.add(sp, BorderLayout.CENTER);
	}
	

			public static void main(String[] args) {
				//EventQueue.invokeLater(new Runnable() {
					//public void run() {
					//	try {
							ViewPurchasedBooks frame = new ViewPurchasedBooks();
							frame.setVisible(true);
						//} catch (Exception e) {
							//e.printStackTrace();
						//}
					}
				//});
			}



