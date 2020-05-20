package MainApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Iterator;

public class RegisterPage extends JFrame implements ActionListener{
	private JTextField username;
	private JPasswordField password;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel repeatPasswordLabel;

	private JPasswordField repeatPassword;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JButton register;
	public RegisterPage()
	{
		getContentPane().setBackground(new Color(65, 105, 225));
		getContentPane().setForeground(new Color(65, 105, 225));
		
		username = new JTextField();
		username.setBounds(250, 100, 150, 25);
		password = new JPasswordField();
		password.setBounds(250, 150, 150, 25);
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(175, 100, 150, 25);
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(175, 150, 150, 25);
		
		repeatPasswordLabel = new JLabel("Repeat password:");
		repeatPasswordLabel.setLocation(135, 200);
		repeatPasswordLabel.setSize(109, 25);
		
		repeatPassword=new JPasswordField();
		repeatPassword.setSize(150, 25);
		repeatPassword.setLocation(250, 200);
		
		radioButton1 = new JRadioButton("trainer");
		radioButton1.setBackground(new Color(65, 105, 225));
		radioButton1.setBounds(225, 250, 100, 30);
		radioButton2 = new JRadioButton("gym user");
		radioButton2.setBackground(new Color(65, 105, 225));
		radioButton2.setBounds(325, 250, 100, 30);
		register = new JButton("Register");
		register.setLocation(275, 300);
		register.setSize(100, 30);
		register.addActionListener(this);
		

    	ButtonGroup bg = new ButtonGroup();
    	bg.add(radioButton1);
    	bg.add(radioButton2);
    	//passwordLabel.setBounds(175,150,150,25);
    	
    	//radioButton1.setBounds(225,200,100,30);
    	//radioButton2.setBounds(325,200,100,30);
    	
    	//register.setBounds(258,250,100,30);
    	getContentPane().setLayout(null);
    	
    	getContentPane().add(radioButton1);
    	getContentPane().add(radioButton2);
    	getContentPane().add(username);
    	getContentPane().add(password);
    	getContentPane().add(usernameLabel);
    	getContentPane().add(passwordLabel);
    	getContentPane().add(repeatPasswordLabel);
    	getContentPane().add(repeatPassword);
    	getContentPane().add(register);
    	
    	
    	
    	
    	
		
		this.setTitle("Register Page");
		
		this.setSize(600,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String  usernameField = username.getText();
		String passwordField = password.getText();
		String repeatPasswordField = repeatPassword.getText();
		JButton clicked = (JButton)e.getSource();
		
		JSONParser parser=new JSONParser();
	    JSONArray jsonArray;
	    try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
	    	jsonArray = (JSONArray)parser.parse(reader);
	    	boolean isThere=false;
	    	Iterator<JSONObject> it = jsonArray.iterator();
			while (it.hasNext() && isThere!=true) {
			JSONObject obj = it.next();
			String userName = obj.get("username").toString();
			if(userName.contentEquals(usernameField))
				isThere=true;
			}
	    
	    if(isThere)
	    {
	    	JOptionPane.showMessageDialog(this,"Username already taken!");
	    }
	    else if(usernameField!=null && passwordField.equals(repeatPasswordField) && clicked == register && radioButton1.isSelected())
	    {


	    	JSONObject obj = new JSONObject();
	    	obj.put("username", usernameField);
	    	obj.put("password", passwordField);
	        obj.put("role" , "trainer");
	        obj.put("group","aba");
	        jsonArray.add(obj);
	        
	    
	    	
	   
	    	
	    
	    try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
	    file.write(jsonArray.toJSONString());
	    file.flush();
	    
	    dispose();
	    } catch (IOException h) {
	    h.printStackTrace();
	    }
	    }
	    else if(usernameField!=null && passwordField.equals(repeatPasswordField) && clicked == register && radioButton2.isSelected())
	    {
	    	Encrypt(passwordField);

	    	JSONObject obj = new JSONObject();
	    	obj.put("username", usernameField);
	    	obj.put("password", passwordField);
	        obj.put("role" , "gymUser");
	        obj.put("membershipType",null);
	        obj.put("group","unassigned");
	        jsonArray.add(obj);
	        
	        
	    
	    
	    
	    try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
	    file.write(jsonArray.toJSONString());
	    file.flush();
	    
	    dispose();
	    } catch (IOException h) {
	    h.printStackTrace();
	    }
	    
	    }
	    else if(usernameField!=null && !passwordField.equals(repeatPasswordField) && clicked == register && (radioButton2.isSelected() || radioButton1.isSelected()))
	    {
	    	JOptionPane.showMessageDialog(this,"Password must match!");
	    }
	    
	  
	    
	}catch (IOException h) {
		h.printStackTrace();
		} catch (ParseException h) {
		h.printStackTrace();
		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			noSuchAlgorithmException.printStackTrace();
		} catch (ShortBufferException shortBufferException) {
			shortBufferException.printStackTrace();
		} catch (NoSuchPaddingException noSuchPaddingException) {
			noSuchPaddingException.printStackTrace();
		} catch (BadPaddingException badPaddingException) {
			badPaddingException.printStackTrace();
		} catch (NoSuchProviderException noSuchProviderException) {
			noSuchProviderException.printStackTrace();
		} catch (IllegalBlockSizeException illegalBlockSizeException) {
			illegalBlockSizeException.printStackTrace();
		} catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
			invalidAlgorithmParameterException.printStackTrace();
		} catch (InvalidKeyException invalidKeyException) {
			invalidKeyException.printStackTrace();
		}


	}

	private final void Encrypt(String passwordField) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, ShortBufferException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {

		JSONObject obj = new JSONObject();
		char[] pw = password.toString().toCharArray();

		/*Security.addProvider(new org.bouncycastle....);
		byte[] input = passwordField.getBytes();
		byte[] keyBytes = "abc".getBytes();
		byte[] ivBytes = "blablabla".getBytes();
		SecretKeySpec key = new SecretKeySpec(keyBytes,"DES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		Cipher cipher = Cipher.getInstance("DES/CTR/NoPadding","BC");
		cipher.init(Cipher.ENCRYPT_MODE,key,ivSpec);
		byte[] cipherText;
		cipherText = new byte[cipher.getOutputSize(input.length)];
		int ctLength = cipher.update(input,0,input.length,cipherText,0);
		ctLength += cipher.doFinal(cipherText,ctLength);
		pw.setText(new String(cipherText));*/
		obj.put("ePassword",pw);



	}
}
