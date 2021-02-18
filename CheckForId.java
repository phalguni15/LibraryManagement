package Project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CheckForId extends JFrame implements ActionListener {
	JTextField unique;
	
	JLabel niq;
	JFrame j;
	JButton j1;
	Connection con;
	PreparedStatement ps;
	CheckForId()
	{
		
		j=new JFrame("Check for unique id");
			j.setSize(400,400);
			j.getContentPane().setBackground(Color.pink);
			
			niq=new JLabel("Unique id :");niq.setBounds(50, 120, 200, 50);j.add(niq);
			unique=new JTextField();unique.setBounds(150, 120, 100, 50);j.add(unique);
			j1=new JButton("Check it");j1.setBounds(100,250,100,50);j.add(j1);j1.addActionListener(this);
			j.setLayout(null);j.setVisible(true);
			
	}
		@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==j1)
		{
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				ps=con.prepareStatement("SELECT * FROM myguests WHERE Unique_Id=?");
				ps.setLong(1,Long.parseLong(unique.getText()));
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(j, "Id is not unique.Check  another");
				}
				else if(unique.getText().equals(""))
				{
					JOptionPane.showMessageDialog(j, "Fill the field");
				}
				else JOptionPane.showMessageDialog(j, "Id is unique. You can use");
				unique.setText("");
					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
		public static void main(String args[])
		{
			new CheckForId();
		}

}
