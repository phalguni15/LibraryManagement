package Project;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

//import com.wipro.flight.util.DBUtil;
public class AddBook extends JFrame implements ActionListener{
	public JTextField name,phn_no,pickadd,b_title,b_cost,b_cond,b_aut;
	JLabel n,p,pi,bt,bco,bcon,ba;
	JFrame j;
	JButton j1;
	Connection con;
	PreparedStatement ps;
//public ArrayList<BookD> al=new ArrayList<BookD>();
	AddBook()
	{
		JFrame j=new JFrame("Add book");
		j.setSize(600,650);
		j.getContentPane().setBackground(Color.pink);
		n=new JLabel("Name:");n.setBounds(50,50,100,50);j.add(n);
		
		p=new JLabel("Unique id  :");p.setBounds(50, 120, 100, 50);j.add(p);
		
		pi=new JLabel("Address:");pi.setBounds(50, 190, 100, 50);j.add(pi);
		
		bt=new JLabel("Book title:");bt.setBounds(50, 260, 100, 50);j.add(bt);
		
		ba=new JLabel("Book author");ba.setBounds(50,320,100,50);j.add(ba);
		
		bco=new JLabel("Book Mrp:");bco.setBounds(50, 390, 100, 50);j.add(bco);
		
		bcon=new JLabel("Book condition:");bcon.setBounds(50, 460, 100, 50);j.add(bcon);
		
		name=new JTextField();name.setBounds(150,50,200,50);j.add(name);
		
		phn_no=new JTextField();phn_no.setBounds(150,120,200,50);j.add(phn_no);
		
		pickadd=new JTextField();pickadd.setBounds(150,190,200,50);j.add(pickadd);
		
		b_title=new JTextField();b_title.setBounds(150,260,200,50);j.add(b_title);
		
		b_aut=new JTextField();b_aut.setBounds(150,320,200,50);j.add(b_aut);
		
		b_cost=new JTextField();b_cost.setBounds(150,390,200,50);j.add(b_cost);
		
		b_cond=new JTextField();b_cond.setBounds(150,460,200,50);j.add(b_cond);
		
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
	/**
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==j1)
		{
			int count=0;
			 long n=Long.parseUnsignedLong(phn_no.getText());
			 while (n != 0) {
			        n /= 10;     // n = n/10
			        ++count;
			    }
			 try {
				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				 PreparedStatement ps=con.prepareStatement("SELECT * FROM myguests WHERE Unique_Id=?");
				 ps.setString(1, phn_no.getText());
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					JOptionPane.showMessageDialog(j, "Enter unique id");
			
			

			
				else if(name.getText().equals("")||phn_no.getText().equals("")||pickadd.getText().equals("")||b_title.getText().equals("")||b_cost.getText().equals("")||b_cond.getText().equals("")||b_aut.getText().equals(""))
			{
				JOptionPane.showMessageDialog(j, "fill all the fields");
			}
			else if(count!=3)
				JOptionPane.showMessageDialog(j, "invalid id.Keep a three digit number");
			else {
			insertDB();
			name.setText("");
			phn_no.setText("");
			pickadd.setText("");
			b_title.setText("");
			b_cost.setText("");
			b_cond.setText("");
			b_aut.setText("");
			JOptionPane.showMessageDialog(j, "added");
			}
			 }
			 catch(Exception e1)
			 {
				 e1.printStackTrace();
			 }
		}
	//	for(BookD b:al)
		//	System.out.print(b.bookn+"  "+b.bookc+" "+b.bookwri+"  "+b.cond);
		
	}
	
	
	void insertDB()
	{
		int tap=0;
		try {
			ps=con.prepareStatement("insert into myguests value(?,?,?,?,?,?,?)");
			
			ps.setString(1, name.getText());
			ps.setString(2, phn_no.getText());
			ps.setString(3, pickadd.getText());
			ps.setString(4, b_title.getText());
			ps.setString(5,b_aut.getText() );
			ps.setString(6, b_cost.getText());
			ps.setString(7,b_cond.getText());
			
			tap=ps.executeUpdate();
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void main(String args[])
	{
		new AddBook();
		
	}


class BookD{
	public String bookn;
	public int bookc;
	public String bookwri;
	public String cond;
	BookD(String bookn,int bookc,String bookwri,String cond)
	{
		this.bookn=bookn;
		
		this.bookc=bookc;
		this.bookwri=bookwri;
		this.cond=cond;
	}
	
}
}
