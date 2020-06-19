package MainApp;

import AbstractTypes.Trainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

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
		this.setTitle("Trainer page");
		this.setSize(1280,640);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.sendOrderSuggestion = new JButton("Send admin order suggestion");
		suggestionFrame = new JFrame("Suggestion Page");
		sendOrderSuggestion.addActionListener(this);
		sendOrderSuggestion.setBounds(1000,300,200,50);
		editUserGroup = new JButton("Edit User Group");
		addSuggestiontTextArea = new JTextArea();
		editUserGroup.addActionListener(this);
		sendSuggestion = new JButton("Send suggestion");
		editUserGroup.setBounds(50,300,200,50);
		this.add(editUserGroup);
		this.add(sendOrderSuggestion);
		getContentPane().setBackground(new Color(65, 105, 225));
		ImageIcon icon = new ImageIcon("src/main/java/Resources/weightlifting.png");
		JLabel background = new JLabel(icon);
		background.setBounds(325,100,600,500);
		this.add(background);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if(clicked == editUserGroup){

			new EditUserGroup(trainer);
		}

		if(clicked == sendOrderSuggestion) {

			suggestionFrame.setSize(700,600);
			suggestionFrame.setVisible(true);
			suggestionFrame.setLayout(null);
			suggestionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			addSuggestiontTextArea.setBounds(300,100,200,100);
			sendSuggestion.setBounds(300,400,100,50);
			suggestionFrame.add(addSuggestiontTextArea);
			suggestionFrame.add(sendSuggestion);
			sendSuggestion.addActionListener(this);




		}
		if(clicked == sendSuggestion)
		{
			JSONArray jsonArray = new JSONArray();
			JSONParser parser = new JSONParser();

			try (Reader reader = new FileReader("src/main/java/Resources/suggestions.json")) {
				jsonArray = (JSONArray) parser.parse(reader);

			} catch (IOException | ParseException h) {
				h.printStackTrace();
			}
			JSONObject obj = new JSONObject();
			obj.put("username",this.trainer.getUsername());
			obj.put("suggestion",addSuggestiontTextArea.getText());
			obj.put("role","trainer");

			jsonArray.add(obj);
			try (FileWriter file = new FileWriter("src/main/java/Resources/suggestions.json")) {
				file.write(jsonArray.toJSONString());
				file.flush();


			} catch (IOException h) {
				h.printStackTrace();
			}
			System.out.println(jsonArray);
		}
	}


}
