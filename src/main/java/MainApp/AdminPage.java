package MainApp;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import AbstractTypes.Administrator;

public class AdminPage extends JFrame implements ActionListener{

	private JButton orderMenuButton;
	private JButton manageMembershipsButton;
	private Administrator admin;
	private  JLabel backgroundImageLabel;
	
	public AdminPage(Administrator admin) {
		initialize(admin);


	}

	private void initialize(Administrator admin) {
		JLabel text = new JLabel("Welcome " +admin.getUsername() + "!");
		this.admin = admin;
		text.setBounds(450,0,600,150);
		text.setFont(new Font("Helvetica Neue", Font.BOLD, 40));


		orderMenuButton = new JButton("Order Equipment");
		orderMenuButton.setBounds(10,450,200,50);

		manageMembershipsButton = new JButton("Manage Memberships");
		manageMembershipsButton.setBounds(1050,450,200,50);

		orderMenuButton.addActionListener(this);
		manageMembershipsButton.addActionListener(this);
		ImageIcon background = new ImageIcon("src/main/java/Resources/gym (4).png");
		this.setSize(1280,640);
		this.add(text);
		this.add(orderMenuButton);
		this.add(manageMembershipsButton);
		backgroundImageLabel = new JLabel(background);
		backgroundImageLabel.setBounds(200,100,860,500);
		this.add(backgroundImageLabel);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(65, 105, 225));
	}
	
	/*public static void main(String[] args)
	{
		Administrator admin = new Administrator("Ionut Grigorut Atimut");
		new AdminPage(admin);
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == orderMenuButton)
		{
			new OrderEquipmentPage(admin);

		}
		if(clicked == manageMembershipsButton)
		{
			new ManageMembershipsPage("Manage Memberships");
		}
	}
}
