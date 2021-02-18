package Project;

//p//ublic class Purchase {
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.*;
	import java.util.ArrayList;

	import javax.swing.*;

	//import com.wipro.flight.util.DBUtil;
	public class Purchase extends JFrame implements ActionListener{
		public JTextField name,phn_no,pickadd,b_title,b_cost,b_cond,b_aut,uq;
		JLabel n,p,pi,bt,bco,bcon,ba;
		JFrame j;
		JButton j1;
		Connection con;
		PreparedStatement ps;
	//public ArrayList<BookD> al=new ArrayList<BookD>();
		Purchase()
		{
			JFrame j=new JFrame("Add book");
			j.setSize(600,650);
			j.getContentPane().setBackground(Color.pink);
			//n=new JLabel("Name:");n.setBounds(50,50,100,50);j.add(n);
			
			p=new JLabel("Unique id :");p.setBounds(50, 120, 100, 50);j.add(p);
			
			//pi=new JLabel("Address:");pi.setBounds(50, 190, 100, 50);j.add(pi);
			
			bt=new JLabel("Book author:");bt.setBounds(50, 190, 100, 50);j.add(bt);
			
			ba=new JLabel("Address");ba.setBounds(50,260,100,50);j.add(ba);
			
		//	bco=new JLabel("Book Mrp:");bco.setBounds(50, 390, 100, 50);j.add(bco);
			
			//bcon=new JLabel("Book condition:");bcon.setBounds(50, 460, 100, 50);j.add(bcon);
			
		//	name=new JTextField();name.setBounds(150,50,200,50);j.add(name);
			
			uq=new JTextField();uq.setBounds(150,120,200,50);j.add(uq);
			
			
			
			
			
			b_aut=new JTextField();b_aut.setBounds(150,190,200,50);j.add(b_aut);
			pickadd=new JTextField();pickadd.setBounds(150,260,200,50);j.add(pickadd);
			
			
		//	b_cost=new JTextField();b_cost.setBounds(150,390,200,50);j.add(b_cost);
			
			//b_cond=new JTextField();b_cond.setBounds(150,460,200,50);j.add(b_cond);
			
			j1=new JButton("Add");
			j1.setBounds(100,530,100,50);j.add(j1);j1.addActionListener(this);
			j.setLayout(null);j.setVisible(true);
			con=null;
			
			try {
				//Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				System.out.println("connection done");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==j1)
		{
			Connection con;
			try {
				int t=0;
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				int a=Integer.parseInt(uq.getText());
				String b=b_aut.getText();
			PreparedStatement ps=con.prepareStatement("SELECT Name FROM myguests WHERE Unique_Id=? AND Book_author=?");
			
		ps.setLong(1,Long.parseLong(uq.getText()));
		System.out.println(uq.getText()+" "+b_aut.getText());
		ps.setString(2, b_aut.getText());
		ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				PreparedStatement cs=con.prepareStatement("insert into purchase values(?,?,?)");
				cs.setString(1, uq.getText());
				cs.setString(2, b_aut.getText());
				cs.setString(3, pickadd.getText());
				t=cs.executeUpdate();
				JOptionPane.showMessageDialog(j,"successfully purchased");
			}
			else
				JOptionPane.showMessageDialog(j, "Please enter valid id or author name");
			PreparedStatement cd=con.prepareStatement("Delete FROM myguests WHERE Unique_id=? AND Book_author=?");
			cd.setLong(1,Long.parseLong(uq.getText()));
			//System.out.println(uq.getText()+" "+b_aut.getText());
			cd.setString(2, b_aut.getText());
			t=cd.executeUpdate();
			uq.setText("");
			b_aut.setText("");
			pickadd.setText("");
				
					
		}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		
	}
	}
	public static void main(String args[])
	{
		new Purchase();
	}
}
