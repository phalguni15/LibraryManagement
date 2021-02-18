package Project;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainPage extends JFrame implements ActionListener{
	Connection con;
	PreparedStatement ps;
	JButton j1,j2,j3,j4,j5;
	JFrame j;
	private JPanel contentPane;
	private JTable table;
	MainPage()
	{
		JFrame j=new JFrame("Novels");
		j.setSize(800,1000);
		j.getContentPane().setBackground(Color.PINK);
		 j1=new JButton("Add books");
		 j1.setBounds(300,150,175,100);
		 j2=new JButton("View books");
		 j2.setBounds(300,300,175,100);
		 j3=new JButton("Purchase book");
		 j3.setBounds(300,450,175,100);
		 j4=new JButton("View Purchased Books");
				 j4.setBounds(300,600,175,100);
				 j5=new JButton("Check for unique id");
				 j5.setBounds(300,750,175,100);
		 j.add(j1);j1.addActionListener(this);
		 j.add(j2);j2.addActionListener(this);
		 j.add(j3);j3.addActionListener(this);
		 j.add(j4);j4.addActionListener(this);
		 j.add(j5);j5.addActionListener(this);
		 j.setLayout(null);
		 j.setVisible(true);
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		ViewBooks frame;
		ViewPurchasedBooks frame1;
		// TODO Auto-generated method stub\
		if(e.getSource()==j1)
		{
			new AddBook();
		}
		else if(e.getSource()==j2) {
			frame=new ViewBooks();
			frame.setVisible(true);
		}
		else if(e.getSource()==j3)
			new Purchase();
		else if(e.getSource()==j4) {
			frame1=new ViewPurchasedBooks();
			frame1.setVisible(true);
		}
		else if(e.getSource()==j5)
			new CheckForId();
		
	}
	public static void main(String args[])
	{
		new MainPage();
	}

}
