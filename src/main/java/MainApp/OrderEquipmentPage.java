package MainApp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
	
	public OrderEquipmentPage(Administrator admin)
	{
		this.admin = admin;
		this.setTitle("Order Equipment Page");
		orderArea = new JTextArea();
		placeOrder = new JButton("Place order");
		orderButton = new JButton("Order equipment");
		orderArea = new JTextArea();
		checkTrainerSuggestion = new JButton("Check trainer suggestion");
		orderButton.setBounds(50,200,200,100);
		orderButton.addActionListener(this);
		placeOrder.setBounds(175,400,200,100);

		orderFrame = new JFrame("Order equipment");
		orderArea.setBounds(124,150,300,200);
		orderFrame.add(orderArea);
		orderFrame.setSize(600,600);
		orderFrame.setLayout(null);
		orderFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		orderFrame.add(placeOrder);
		placeOrder.addActionListener(this);
		checkTrainerSuggestion.addActionListener(this);
		checkTrainerSuggestion.setBounds(325,200,200,100);
		this.add(orderButton);
		this.add(checkTrainerSuggestion);
		this.setSize(600,600);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		model = new DefaultTableModel();

		checkSuggestionFrame = new JFrame("Suggestions from trainers");
		checkSuggestionFrame.setSize(800,800);
		checkSuggestionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
