package MainApp;
import AbstractTypes.GymUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymUserPage extends JFrame implements ActionListener {
	private JButton manageAccount;
	private JButton sendTrainerSuggestion;
	private JButton seeWorkoutProgamAndClass;

	public GymUserPage(GymUser user)
	{
		JLabel text = new JLabel("Welcome " + user.getUsername());
		text.setBounds(500,120,400 + user.getUsername().length(),90 );
		text.setFont(new Font("Tahoma",Font.PLAIN,40));
		manageAccount = new JButton("Change account details");
		manageAccount.setBounds(40,400,250,90);
		manageAccount.addActionListener(this);
		manageAccount.setFont(new Font("Tahoma", Font.PLAIN,20));
		sendTrainerSuggestion= new JButton("Send Suggestion");
		sendTrainerSuggestion.setBounds(500,400,250,90);
		sendTrainerSuggestion.addActionListener(this);
		sendTrainerSuggestion.setFont(new Font("Tahoma", Font.PLAIN,20));
		seeWorkoutProgamAndClass = new JButton(("See class and workout"));
		seeWorkoutProgamAndClass.setBounds(960,400,250,90);
		seeWorkoutProgamAndClass.addActionListener(this);
		seeWorkoutProgamAndClass.setFont(new Font("Tahoma", Font.PLAIN,20));
		this.add(seeWorkoutProgamAndClass);
		this.add(sendTrainerSuggestion);
		this.add(manageAccount);
		this.setSize(1280,640);
		this.add(text);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if(clicked == manageAccount);
		{
			System.out.println("Change details");
		}
	}
}