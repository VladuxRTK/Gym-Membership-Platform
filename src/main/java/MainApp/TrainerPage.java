package MainApp;

import AbstractTypes.Trainer;
import AuxiliaryStuff.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class TrainerPage extends JFrame implements ActionListener {

	private Trainer trainer;
	private JButton editUserGroup;
	private JButton sendOrderSuggestion;
	private JFrame suggestionFrame;
	private JTextArea addSuggestiontTextArea;
	private JButton sendSuggestion;
	private String message;
	private JFrame seeSuggestions;
	private JTable table;
	private JScrollPane pane;
	private DefaultTableModel model;
	private JButton seeSuggestionsButton;


	public TrainerPage(Trainer trainer)
	{
		model = new DefaultTableModel();


		seeSuggestionsButton = new JButton("See suggestion");
		seeSuggestionsButton.addActionListener(this);
		seeSuggestionsButton.setBounds(525,50,200,50);
		this.trainer = trainer;
		seeSuggestions = new JFrame("Check suggestion");
		this.setTitle("Trainer page");
		this.setSize(1280,640);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.sendOrderSuggestion = new JButton("Send order suggestion");
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
		this.add(seeSuggestionsButton);

		seeSuggestions.setSize(880,640);
		seeSuggestions.setLayout(null);
		seeSuggestions.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		seeSuggestions.getContentPane().setBackground(new Color(65, 105, 225));;
		createTable();

	}

	public void createTable()
	{
		JSONArray jsonArray = new JSONArray();
		JSONParser parser = new JSONParser();

		jsonArray= JSONReader.readJSON("src/main/java/Resources/suggestions.json",parser);

		int counter=0;



		Object[] column ={"NAME","SUGGESTION"};
		model.setColumnIdentifiers(column);
		table = new JTable();
		Object[] row = new Object[2];
		table.setModel(model);
		Iterator<JSONObject> it = jsonArray.iterator();
		while(it.hasNext())
		{
			JSONObject obj = it.next();
			if(obj.get("role").toString().equals("gymUser") && obj.get("group").toString().equals(trainer.getGroup()))
			{
				row[0] = obj.get("username").toString();
				row[1] = obj.get("suggestion").toString();
				model.addRow(row);
			}
		}

		//suggestionTable.setBounds(400,400,200,300);
		pane =new JScrollPane(table);
		pane.setBounds(0, 0, 880, 640);




		seeSuggestions.add(pane);



	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if (clicked == editUserGroup) {

			new EditUserGroup(trainer);
		}

		if (clicked == sendOrderSuggestion) {

			suggestionFrame.setSize(700, 600);
			suggestionFrame.setVisible(true);
			suggestionFrame.setLayout(null);
			suggestionFrame.getContentPane().setBackground(new Color(65, 105, 225));
			suggestionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			addSuggestiontTextArea.setBounds(225, 100, 200, 100);
			sendSuggestion.setBounds(225, 400, 200, 50);
			suggestionFrame.add(addSuggestiontTextArea);
			suggestionFrame.add(sendSuggestion);
			sendSuggestion.addActionListener(this);


		}
		if (clicked == sendSuggestion) {
			sendSuggestionToAdmin();
			suggestionFrame.dispose();
		}

		if (clicked == seeSuggestionsButton)
		{

			seeSuggestions.setVisible(true);
		}
	}

	public void setSuggestionText(String text)
	{
		addSuggestiontTextArea.setText(text);
		message = "set";
	}
	public String getSuggestionText()
	{
		message="get";

		return addSuggestiontTextArea.getText();
	}
	public String getMessage()
	{
		return message;
	}

	public void  sendSuggestionToAdmin()
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
			message = "Added";


		} catch (IOException h) {
			h.printStackTrace();
		}
		System.out.println(jsonArray);
	}
	}



