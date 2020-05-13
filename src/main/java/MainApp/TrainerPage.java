package MainApp;

import AbstractTypes.Trainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainerPage extends JFrame implements ActionListener {

	private Trainer trainer;
	private JButton editUserGroup;

	public TrainerPage(Trainer trainer)
	{
		this.trainer = trainer;
		this.setSize(1280,640);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		editUserGroup = new JButton("Edit User Group");

		editUserGroup.addActionListener(this);

		editUserGroup.setBounds(300,300,200,50);
		this.add(editUserGroup);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if(clicked == editUserGroup){

			new EditUserGroup(trainer);
		}
	}


}
