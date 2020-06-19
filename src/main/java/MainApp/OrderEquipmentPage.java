package MainApp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import AbstractTypes.Administrator;
import AuxiliaryStuff.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class OrderEquipmentPage extends JFrame implements ActionListener {

	private JButton orderButton;
	private JButton checkTrainerSuggestion;
	private JButton placeOrder;
	private JTable suggestionTable;
	private JScrollPane jp;
	private Scanner s;
	private File file;
	private JFrame orderFrame;
	private JFrame checkSuggestionFrame;
	private JTextArea orderArea;
	private ArrayList<String> suggestionList = new ArrayList<String>();
	private Administrator admin;
	private DefaultTableModel model;
	private JLabel backgroundImageLabel;
	
	public OrderEquipmentPage(Administrator admin)
	{
		initialize(admin);
	}

	private void initialize(Administrator admin) {
		this.admin = admin;
		this.setTitle("Order Equipment Page");
		orderArea = new JTextArea();
		placeOrder = new JButton("Place order");
		orderButton = new JButton("Order equipment");
		orderArea = new JTextArea();
		checkTrainerSuggestion = new JButton("Check trainer suggestion");
		orderButton.setBounds(30,200,200,100);
		orderButton.addActionListener(this);
		placeOrder.setBounds(175,400,200,100);

		orderFrame = new JFrame("Order equipment");
		orderArea.setBounds(124,150,300,200);
		orderFrame.add(orderArea);
		orderFrame.setSize(600,600);
		orderFrame.setLayout(null);
		orderFrame.getContentPane().setBackground(new Color(65, 105, 225));
		orderFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		orderFrame.add(placeOrder);
		ImageIcon orderIcon = new ImageIcon("src/main/java/Resources/dumbbell (3).png");
		JLabel orderBackgroundLabel = new JLabel(orderIcon);
		orderBackgroundLabel.setBounds(175,0,200,200);
		orderFrame.add(orderBackgroundLabel);
		placeOrder.addActionListener(this);
		checkTrainerSuggestion.addActionListener(this);
		checkTrainerSuggestion.setBounds(1030,200,200,100);
		this.add(orderButton);
		this.add(checkTrainerSuggestion);
		this.setSize(1280,640);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		model = new DefaultTableModel();

		checkSuggestionFrame = new JFrame("Suggestions from trainers");
		checkSuggestionFrame.getContentPane().setBackground(new Color(65, 105, 225));
		checkSuggestionFrame.setSize(800,800);
		checkSuggestionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createTable();
		ImageIcon background = new ImageIcon("src/main/java/Resources/weight.png");
		getContentPane().setBackground(new Color(65, 105, 225));
		backgroundImageLabel = new JLabel(background);
		backgroundImageLabel.setBounds(200,100,800,500);
		this.add(backgroundImageLabel);
	}


	public void createTable()
	{
		JSONArray jsonArray = new JSONArray();
		JSONParser parser = new JSONParser();

		jsonArray= JSONReader.readJSON("src/main/java/Resources/suggestions.json",parser);

		int counter=0;



		Object[] column ={"NAME","SUGGESTION"};
		model.setColumnIdentifiers(column);
		suggestionTable = new JTable();
		Object[] row = new Object[2];
		suggestionTable.setModel(model);
		Iterator<JSONObject> it = jsonArray.iterator();
		while(it.hasNext())
		{
			JSONObject obj = it.next();
			if(obj.get("role").toString().equals("trainer"))
			{
				row[0] = obj.get("username").toString();
				row[1] = obj.get("suggestion").toString();
				model.addRow(row);
			}
		}

		//suggestionTable.setBounds(400,400,200,300);
		jp =new JScrollPane(suggestionTable);
		jp.setBounds(400,400,200,300);

		

		checkSuggestionFrame.add(jp);

	    
	  
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton)e.getSource();
		String data = orderArea.getText().trim();
		if(clicked == checkTrainerSuggestion)
		{
			checkSuggestionFrame.setVisible(true);
		}
		if(clicked == orderButton)
		{
			orderFrame.setVisible(true);
		}
		if(clicked == placeOrder && data.length()>0)
		{
			
		    admin.orderEquipment();
		}
	}
}
