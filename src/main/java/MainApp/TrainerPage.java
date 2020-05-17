package MainApp;

import AbstractTypes.Trainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainerPage extends JFrame implements ActionListener {

	private Trainer trainer;
	private JButton editUserGroup;
	private JButton sendOrderSuggestion;
	private JFrame suggestionFrame;
	private JTextArea addSuggestiontTextArea;
	private JButton sendSuggestion;

	public TrainerPage(Trainer trainer)
	{
		this.trainer = trainer;
		this.setSize(1280,640);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.sendOrderSuggestion = new JButton("Send admin order suggestion");
		suggestionFrame = new JFrame("Suggestion Page");
		sendOrderSuggestion.addActionListener(this);
		sendOrderSuggestion.setBounds(300,500,200,50);
		editUserGroup = new JButton("Edit User Group");
		addSuggestiontTextArea = new JTextArea();
		editUserGroup.addActionListener(this);
		sendSuggestion = new JButton("Send suggestion");
		editUserGroup.setBounds(300,300,200,50);
		this.add(editUserGroup);
		this.add(sendOrderSuggestion);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if(clicked == editUserGroup){

			new EditUserGroup(trainer);
		}
	}


}
