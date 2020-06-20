package MainApp;
import AbstractTypes.GymUser;
import AuxiliaryStuff.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class GymUserPage extends JFrame implements ActionListener {
	private JButton manageAccount;
	private JButton sendTrainerSuggestion;
	private JButton seeWorkoutProgamAndClass;
	private GymUser gymUser;
	private JFrame seeWorkout;
	private JLabel groupName;
	private JTextArea exercises;
	private JSONArray jsonArray;
	private JSONParser parser;
	private JFrame sendTrainerSuggestionFrame;
	private JTextArea suggestionArea;
	private JButton send;
	private JButton membership;
	private String message;


	public GymUserPage(GymUser gymUser)
	{
		this.gymUser = gymUser;
		JLabel text = new JLabel("Welcome " + gymUser.getUsername());
		text.setBounds(500,120,400 + gymUser.getUsername().length(),90 );
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
		this.getContentPane().setBackground(new Color(65, 105, 225));
		parser = new JSONParser();
		exercises = new JTextArea();
		exercises.setEditable(false);
		exercises.setBounds(640,220,300,100);
		seeWorkout = new JFrame();
		seeWorkout.setLayout(null);
		seeWorkout.setSize(1280,640);
		seeWorkout.add(exercises);
		seeWorkout.setTitle("Workout and group");
		jsonArray = new JSONArray();
		seeWorkout.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		send = new JButton("Send");
		send.setBounds(500,400,250,90);
		suggestionArea = new JTextArea();
		suggestionArea.setBounds(475,150,300,200);
		send.addActionListener(this);
		sendTrainerSuggestionFrame = new JFrame("Send suggestion");
		sendTrainerSuggestionFrame.setSize(1280,640);
		sendTrainerSuggestionFrame.setLayout(null);
		sendTrainerSuggestionFrame.add(send);
		sendTrainerSuggestionFrame.add(suggestionArea);
		sendTrainerSuggestionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		sendTrainerSuggestionFrame.getContentPane().setBackground(new Color(65, 105, 225));


	}

	public void setSuggestionText(String text)
	{
		this.suggestionArea.setText(text);
	}
	public String getMessage()
	{
		return message;
	}
	public String getSuggestionText(){return this.suggestionArea.getText();}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if(clicked == manageAccount)
		{
			new EditAccount(this.gymUser);
		}

		if(clicked == seeWorkoutProgamAndClass)
		{
			seeWorkout.getContentPane().setBackground(new Color(65, 105, 225));
			seeWorkout.setVisible(true);
			try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
				jsonArray = (JSONArray) parser.parse(reader);

			} catch (IOException h) {
				h.printStackTrace();
			} catch (ParseException h) {
				h.printStackTrace();
			}
			Iterator<JSONObject> it = jsonArray.iterator();
			while(it.hasNext())
			{
				JSONObject obj = it.next();
				if(obj.get("username").toString().equals(this.gymUser.getUsername()) && !obj.get("group").toString().equals("unassigned"))
				{
					exercises.setText(obj.get("exercises").toString());
					groupName = new JLabel("Group " + obj.get("group").toString());
					groupName.setBounds(420,220,200,100);
					groupName.setFont(new Font("Tahoma", Font.PLAIN, 40));
					seeWorkout.add(groupName);
				}
				else if(obj.get("username").toString().equals(this.gymUser.getUsername()) && obj.get("group").toString().equals("unassigned"))
				{
					seeWorkout.setVisible(false);
					JOptionPane.showMessageDialog(null, "You are not in any group","alert" ,JOptionPane.ERROR_MESSAGE);

				}

			}
		}
		if (clicked == sendTrainerSuggestion)
		{
			sendTrainerSuggestionFrame.setVisible(true);

		}
		if(clicked == send)
		{
			sendSuggestion();

		}

	}

	public void sendSuggestion() {
		String suggestion = suggestionArea.getText();
		JSONArray jsonArray2 = new JSONArray();
		JSONArray suggestionArray = new JSONArray();
		JSONParser auxParser = new JSONParser();
		jsonArray2= JSONReader.readJSON("src/main/java/Resources/users.json",auxParser);
		suggestionArray = JSONReader.readJSON("src/main/java/Resources/suggestions.json",auxParser);
		Iterator<JSONObject> it = jsonArray2.iterator();
		while(it.hasNext())
		{
			JSONObject obj = it.next();
			if(obj.get("username").toString().equals(this.gymUser.getUsername()) && obj.get("role").toString().equals("gymUser") && !obj.get("group").equals("unassgined"))
			{
				JSONObject auxObj = new JSONObject();
				auxObj.put("username",this.gymUser.getUsername());
				auxObj.put("group",obj.get("group").toString());
				auxObj.put("suggestion",suggestion);
				auxObj.put("role","gymUser");
				suggestionArray.add(auxObj);
				message = "suggestion";

			}
			else if(obj.get("username").toString().equals(this.gymUser.getUsername()) && obj.get("role").toString().equals("gymUser") && obj.get("group").equals("unassgined")){
				sendTrainerSuggestionFrame.setVisible(false);
				JOptionPane.showMessageDialog(null, "You are not in any group","alert" ,JOptionPane.ERROR_MESSAGE);
			}

		}
		JSONReader.writeJSON("src/main/java/Resources/suggestions.json",suggestionArray);
	}
}