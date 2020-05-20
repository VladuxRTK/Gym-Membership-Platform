package MainApp;
import AbstractTypes.Administrator;
import AbstractTypes.GymUser;
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
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


public class Login extends JFrame implements ActionListener{
	//private JPanel panel;
	private JPasswordField password;
	private JTextField username;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton createAccountButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
  
    
    public Login()
    {
    	super("Login Page");
    	getContentPane().setBackground(new Color(65, 105, 225));
    	this.setSize(600,400);
    	username = new JTextField();
    	password = new JPasswordField();
    	
    	usernameLabel = new JLabel("Username: ");
    	passwordLabel = new JLabel("Password: ");
    	
    	loginButton = new JButton("Login");//action listener to be added
    	createAccountButton = new JButton("Create account");//action listener to be added
    	
    	radioButton1 = new JRadioButton("admin");
    	radioButton1.setBackground(new Color(65, 105, 225));
    	radioButton2 = new JRadioButton("trainer");
    	radioButton2.setBackground(new Color(65, 105, 225));
    	radioButton3 = new JRadioButton("gym user");
    	radioButton3.setBackground(new Color(65, 105, 225));
    	
    		
    	usernameLabel.setBounds(175,100,150,25);
    	passwordLabel.setBounds(175,150,150,25);
    	
    	username.setBounds(250,100,150,25);
    	password.setBounds(250,150,150,25);
    	
    	loginButton.setBounds(250,250,75,25);
    	createAccountButton.setBounds(25,300,125,50);
    	createAccountButton.setFont(new Font("Dialog", Font.PLAIN, 12));
    	
    	radioButton1.setBounds(175,200,100,30);
    	radioButton2.setBounds(275,200,100,30);
    	radioButton3.setBounds(375,200,100,30);
    	
    	loginButton.addActionListener(this);
    	createAccountButton.addActionListener(this);
    	
    		
    	
    	
    	ButtonGroup bg = new ButtonGroup();
    	bg.add(radioButton1);
    	bg.add(radioButton2);
    	bg.add(radioButton3);
    	
    	/*radioButton1.addActionListener(this);
    	radioButton2.addActionListener(this);
    	radioButton3.addActionListener(this);*/
    	
    	getContentPane().add(username);
    	getContentPane().add(password);
    	getContentPane().add(passwordLabel);
    	getContentPane().add(loginButton);
    	getContentPane().add(usernameLabel);
    	getContentPane().add(radioButton1);
    	getContentPane().add(radioButton2);
    	getContentPane().add(radioButton3);
    	getContentPane().add(createAccountButton);
    	
    	getContentPane().setLayout(null);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String passwordField = password.getText();
		String usernameField = username.getText();
		//JButton clicked = (JButton)e.getSource();
		JButton clicked = (JButton)e.getSource();
		boolean isLogged=false;
		JSONParser parser = new JSONParser();
		try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
		JSONArray jsonArray = (JSONArray)parser.parse(reader);
		//System.out.println(jsonArray);
		Iterator<JSONObject> it = jsonArray.iterator();
		while (it.hasNext()) {
		JSONObject obj = it.next();
		String userName = obj.get("username").toString();
		String password = obj.get("password").toString();
		String role = obj.get("role").toString();
	
		
		if(userName.equals(usernameField) && password.equals(passwordField)  && radioButton1.isSelected() && role.equals("admin"))
		{
			Administrator admin = new Administrator("admin");
			
			JOptionPane.showMessageDialog(this,"Logged in as admin");
			new AdminPage(admin);
			isLogged=true;
			dispose();
			
		}
		else if(userName.equals(usernameField) && password.equals(passwordField)  && radioButton2.isSelected() && role.equals("trainer"))
		{
			Trainer trainer = new Trainer(usernameField,obj.get("group").toString());


			JOptionPane.showMessageDialog(this,"Logged in as trainer");
			isLogged=true;
			new TrainerPage(trainer);
			dispose();
		}
		else if(userName.equals(usernameField) && password.equals(passwordField)  && radioButton3.isSelected() && role.equals("gymUser"))
		{
			
			GymUser user=new GymUser(usernameField,passwordField);
			
			isLogged=true;
			JOptionPane.showMessageDialog(this,"Logged in as user");
			new GymUserPage(user);
			dispose();
		}
	
		
		//roleList.add(obj.get("role").toString());
		
		}
		} catch (IOException h) {
		h.printStackTrace();
		} catch (ParseException h) {
		h.printStackTrace();
		}
		if(isLogged==false && clicked != createAccountButton)
		{
			JOptionPane.showMessageDialog(this,"Account not found! Check your credetentials or create an account!");
		}
		
		if(clicked == createAccountButton)
		{
			new RegisterPage();
		}
		
		/*if(usernameField.equals ("admin") && passwordField .equals("admin") && radioButton1.isSelected())
		{
			Administrator admin = new Administrator("admin");
			System.out.println("admin");
			JOptionPane.showMessageDialog(this,"Logged in as admin");
			new AdminPage(admin);
			dispose();
			
		}
		
		if(userNameList.contains(usernameField) && passwordList.contains(passwordField) && roleList.contains("trainer") && radioButton3.isSelected())
		{
			GymUser user=new GymUser(usernameField);
			JOptionPane.showMessageDialog(this,"Logged in as user");
			new GymUserPage(user);
			dispose();
		}
		if( userNameList.contains(usernameField) && passwordList.contains(passwordField) && roleList.contains("gymUser") && radioButton2.isSelected())
		{
			JOptionPane.showMessageDialog(this,"Logged in as trainer");
		}*/
		
		
	}
    
    
	

}
