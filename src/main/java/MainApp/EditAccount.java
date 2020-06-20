package MainApp;

import AbstractTypes.GymUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Base64;
import java.util.Iterator;

import AuxiliaryStuff.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class EditAccount extends JFrame implements ActionListener {

    private JButton changeAccountDetails;
    private JButton changeMemberShip;
    private JLabel currentMembership;
    private GymUser gymUser;
    private JFrame changeDetails;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel repeatPasswordLabel;
    private JButton changeButton;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField repeatPassword;
    private JSONParser parser;
    private JButton change;
    private JSONArray jsonArray;
    private JButton membership;
    private String message;
    public EditAccount(GymUser gymUser) {
        initialize(gymUser);

    }

    public void setUsername(String text)
    {
        this.username.setText(text);
        message = "username";
    }

    public void setPassword(String text)
    {
        this.password.setText(text);
        message ="password";
    }
    public void setRepeatPassword(String text)
    {
        this.repeatPassword.setText(text);
        message = "repeat";
    }
    public String getUsername()
    {
        message="username";
        return username.getText();
    }
    public String getPassword()
    {
        message="password";
        return password.getText();
    }
    public String getRepeatPassword()
    {
        message="repeat";
        return repeatPassword.getText();
    }

    public String getMessage()
    {
        return this.message;
    }


    private void initialize(GymUser gymUser) {
        this.gymUser = gymUser;

        this.setTitle("Edit Account");
        changeAccountDetails = new JButton("Change details");
        changeAccountDetails.setBounds(25, 400, 250, 90);
        changeAccountDetails.addActionListener(this);
        membership = new JButton("Change membership");
        membership.setBounds(500,400,250,90);
        membership.addActionListener(this);
        ImageIcon img = new ImageIcon("src/main/java/Resources/gym (9).png");
        JLabel background = new JLabel(img);
        background.setBounds(200,100,350,300);

        this.setSize(800, 600);
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(changeAccountDetails);
        this.add(membership);
        this.add(background);

        change = new JButton("Change");
        change.setBounds(275,300,100,30);
        changeDetails = new JFrame();
        username = new JTextField();
        username.setBounds(250, 100, 150, 25);
        password = new JPasswordField();
        password.setBounds(250, 150, 150, 25);
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(175, 100, 150, 25);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(175, 150, 150, 25);
        change.addActionListener(this);

        repeatPasswordLabel = new JLabel("Repeat password:");
        repeatPasswordLabel.setLocation(135, 200);
        repeatPasswordLabel.setSize(109, 25);

        repeatPassword = new JPasswordField();
        repeatPassword.setSize(150, 25);
        repeatPassword.setLocation(250, 200);

        changeDetails.setSize(600, 400);
        changeDetails.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        changeDetails.setLayout(null);
        changeDetails.setTitle("Change details");
        changeDetails.add(username);
        changeDetails.add(usernameLabel);
        changeDetails.add(passwordLabel);
        changeDetails.add(password);
        changeDetails.add(repeatPassword);
        changeDetails.add(repeatPasswordLabel);
        changeDetails.add(change);
        changeDetails.getContentPane().setBackground(new Color(65, 105, 225));

        parser = new JSONParser();
        getContentPane().setBackground(new Color(65, 105, 225));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == changeAccountDetails) {
            changeDetails.setVisible(true);
        }
        if(clicked == membership)
        {
            new ManageMembership(this.gymUser);
        }
        if (clicked == change) {

           /* try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
                jsonArray = (JSONArray) parser.parse(reader);

            } catch (IOException | ParseException h) {
                h.printStackTrace();
            }*/

            changeAccountDetails();
            JOptionPane.showMessageDialog(this,"Account details changed!");
            changeDetails.dispose();


            //  }

        }
    }

    public void changeAccountDetails() {
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        System.out.println("Here");
        System.out.print(jsonArray);
        String usernameField;
        if(!username.getText().equals(""))
        usernameField = username.getText();
        else usernameField = this.gymUser.getUsername();
        String passwordField;
        if(!password.getText().equals(""))
            passwordField = password.getText();
        else passwordField = this.gymUser.getPassword();
        System.out.println(passwordField);
        String repeatPasswordField = repeatPassword.getText();
        System.out.println(usernameField);
        JSONArray auxJSON = new JSONArray();
        //  if (usernameField != null && passwordField == null && repeatPasswordField == null) {
        Iterator<JSONObject> it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject obj = it.next();
            if (obj.get("username").toString().equals(gymUser.getUsername())) {
                JSONObject auxObj = new JSONObject();
                passwordField= Base64.getEncoder().encodeToString((passwordField).getBytes());
                auxObj.put("username", usernameField);
                auxObj.put("password", passwordField);
                auxObj.put("role", "gymUser");
                auxObj.put("membershipType", obj.get("membershipType"));
                auxObj.put("group", obj.get("group"));
                auxObj.put("exercises",obj.get("exercises"));
                auxJSON.add(auxObj);
                message = "changed";
            } else {
                auxJSON.add(obj);
            }
        }

        try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
            file.write(auxJSON.toJSONString());
            file.flush();


        } catch (IOException h) {
            h.printStackTrace();
        }
    }
   /* public void changeAccountDetails() {
       /* try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
            jsonArray = (JSONArray) parser.parse(reader);

        } catch (IOException h) {
            h.printStackTrace();
        } catch (ParseException h) {
            h.printStackTrace();
        }*/
        /*jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        System.out.println("Here");
        String usernameField = username.getText();
        String passwordField = password.getText();
        String repeatPasswordField = repeatPassword.getText();
        JSONArray auxJSON = new JSONArray();
        if (usernameField != null && passwordField == null && repeatPasswordField == null) {
            Iterator<JSONObject> it = jsonArray.iterator();
            System.out.println("Passed");
            while (it.hasNext()) {
                JSONObject obj = it.next();
                if (obj.get("username").toString().equals(gymUser.getUsername()) && obj.get("role").toString().equals("gymUser")) {
                    JSONObject auxObj = new JSONObject();
                    auxObj.put("username", usernameField);
                    auxObj.put("password", obj.get("password").toString());
                    auxObj.put("group", obj.get("group").toString());
                    auxObj.put("role", obj.get("role").toString());
                    auxObj.put("membershipType",obj.get("membershipType").toString());


                    auxJSON.add(auxObj);
                } else {
                    auxJSON.add(obj);
                }
            }

                try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                    file.write(auxJSON.toJSONString());
                    file.flush();
                }  catch (IOException h) {
                h.printStackTrace();
            }



        }
    }*/
}