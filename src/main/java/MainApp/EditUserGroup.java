package MainApp;

import AbstractTypes.Trainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

public class EditUserGroup extends  JFrame{

    private JButton addUser;
    private JButton removeUser;
    private JTextArea textArea;
    private JSONParser parser;
    private JSONArray jsonArray;
    private Trainer trainer;

    public EditUserGroup(Trainer trainer){

        this.trainer = trainer;
        this.textArea = new JTextArea("Empty");

        this.setSize(1024,1024);
        this.setLayout(null);
        this.setVisible(true);

        addUser = new JButton("Add");
        removeUser = new JButton("Remove");

        addUser.setBounds(300,600,200,50);
        removeUser.setBounds(300,300,200,50);
        textArea.setBounds(500,500,200,100);

        this.add(textArea);
        this.add(removeUser);
        this.add(addUser);
        this.listUsers();
    }

    public void listUsers (){

        parser=new JSONParser();
        ArrayList<String> arrayJSon= new ArrayList<String>();

        try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
            jsonArray = (JSONArray)parser.parse(reader);

            Iterator<JSONObject> it = jsonArray.iterator();
            while (it.hasNext()) {

                JSONObject obj = it.next();
                if(obj.get("role").toString().equals("gymUser") && obj.get("group").toString().equals(this.trainer.getGroup())){

                    arrayJSon.add(obj.get("username").toString());
                }
            }

            for(String s : arrayJSon) {

                textArea.append(s + "\n");
            }



        }catch (IOException h) {
            h.printStackTrace();

        } catch (ParseException h) {
            h.printStackTrace();
        }

    }

}
