package MainApp;

import AbstractTypes.Trainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

public class EditUserGroup extends  JFrame implements ActionListener {

    private JButton addUser;
    private JButton removeUser;
    private JButton editWP;
    private JTextArea textArea;
    private JSONParser parser;
    private JSONArray jsonArray;
    private Trainer trainer;
    private DefaultTableModel model;
    private JTable table;
    private JTable table2;
    private DefaultTableModel model2;
    private JFrame addFrame;
    private JFrame editFrame;
    private JButton addUser2;
    private JScrollPane pane2;
    private JTextArea workoutText;
    private JButton saveButton;
    private int groupSize;


    public EditUserGroup(Trainer trainer) {

        this.trainer = trainer;

        this.setSize(1280, 640);
        this.setLayout(null);
        this.setVisible(true);

        addUser = new JButton("Add");
        editWP = new JButton("Edit Workout");
        removeUser = new JButton("Remove");
        addUser2 = new JButton("Add");
        saveButton = new JButton("Save");

        workoutText = new JTextArea("");

        addUser.setBounds(300, 300, 150, 25);
        editWP.setBounds(300, 250, 150, 25);
        removeUser.setBounds(300, 200, 150, 25);
        saveButton.setBounds(400,300,150,25);

        model = new DefaultTableModel();
        model2 = new DefaultTableModel();
        table = new JTable();
        table2 = new JTable();

        addFrame = new JFrame("Add gym users");
        editFrame = new JFrame("Edit Gym Workout");

        Object[] columns = {"Assigned"};
        Object[] columns2 = {"Unassigned"};
        model.setColumnIdentifiers(columns);
        model2.setColumnIdentifiers(columns2);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(30);

//

        table2.setBackground(Color.LIGHT_GRAY);
        table2.setForeground(Color.black);
        table2.setFont(font);
        table2.setRowHeight(30);

        // set the model to the table
        table.setModel(model);
        table2.setModel(model2);

        parser = new JSONParser();

        try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
            jsonArray = (JSONArray) parser.parse(reader);

        } catch (IOException h) {
            h.printStackTrace();
        } catch (ParseException h) {
            h.printStackTrace();
        }

        Object[] row = new Object[1];
        Object[] row2 = new Object[1];
        String aux = "unassigned";
        Iterator<JSONObject> it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject obj = it.next();

            if (obj.get("role").toString().equals("gymUser") && obj.get("group").toString().equals(this.trainer.getGroup())) {

                row[0] = obj.get("username").toString();
                model.addRow(row);
            } else if (obj.get("role").toString().equals("gymUser") && obj.get("group").toString().equals(aux)) {

                row2[0] = obj.get("username").toString();
                model2.addRow(row2);

            }
        }

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        pane2 = new JScrollPane(table2);
        pane2.setBounds(0, 0, 880, 200);

        // create JTextFields
        JTextField textAssigned = new JTextField();
        JTextField textUnassigned = new JTextField();

        textAssigned.setBounds(20, 220, 100, 25);
        textUnassigned.setBounds(20, 250, 100, 25);

        this.add(pane);

        // add JTextFields to the jframe
        this.add(removeUser);
        this.add(addUser);
        this.add(editWP);

        getContentPane().setBackground(new Color(65, 105, 225));
        /*this.add(textAssigned);
        this.add(textUnassigned);*/

        removeUser.addActionListener(this);

        editWP.addActionListener(this);

        addUser.addActionListener(this);

        saveButton.addActionListener(this);

           /* @Override
            public void actionPerformed(ActionEvent e) {

                JButton addUser2 = new JButton("Add");

                JButton clicked = (JButton)e.getSource();

                if(clicked == addUser){

                    JFrame addFrame = new JFrame();

                    addFrame.setSize(1280,640);
                    addFrame.setLayout(null);
                    addFrame.setVisible(true);

                    addUser2.setBounds(300,300,150,25);

                    addFrame.add(addUser2);

                    addFrame.add(pane2);

                    table2.addMouseListener(new MouseAdapter(){

                        @Override
                        public void mouseClicked(MouseEvent e){

                            // j = the index of the selected row
                            int j = table2.getSelectedRow();

                            textUnassigned.setText(model2.getValueAt(j, 0).toString());

                        }
                    });

                }

                if(clicked == addUser2){

                    JSONObject obj = new JSONObject();
                    JSONArray auxJS = new JSONArray();

                    Iterator<JSONObject> it = jsonArray.iterator();
                    while (it.hasNext()) {

                        obj = it.next();

                        if(obj.get("username").toString().equals(textUnassigned)){

                            JSONObject auxObj = new JSONObject();

                            auxObj.put("username",textUnassigned.getText());
                            auxObj.put("group","aba");
                            auxObj.put("role","gymUser");
                            auxObj.put("password",obj.get("password"));
                            auxObj.put("membershipType",obj.get("membershipType"));
                            auxJS.add(auxObj);

                            // i = the index of the selected row
                            int i = table2.getSelectedRow();
                            if(i >= 0){
                                // remove a row from jtable
                                model2.removeRow(i);
                            }

                        }
                        else{

                            auxJS.add(obj);
                        }

                    }

                    try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                        file.write(auxJS.toJSONString());
                        file.flush();
                    }catch (IOException h) {
                        h.printStackTrace();
                    }
                }

                /*row[0] = textAssigned.getText();
                row2[0] = textUnassigned.getText();

                // add row to the model
                model.addRow(row);
                JSONObject obj = new JSONObject();
                obj.put("username", row[0]);
                obj.put("username", row2[0]);

                jsonArray.add(obj);

                try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                    file.write(jsonArray.toJSONString());
                    file.flush();
                }catch (IOException h) {
                    h.printStackTrace();
                }
            }
        });*/

        table2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row


                //textAssigned.setText(model.getValueAt(i, 0).toString());

            }
        });
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row


                //textAssigned.setText(model.getValueAt(i, 0).toString());

            }
        });
        editFrame.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row


                //textAssigned.setText(model.getValueAt(i, 0).toString());

            }
        });


    }

    public void listUsers() {

        parser = new JSONParser();
        ArrayList<String> arrayJSon = new ArrayList<String>();

        try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
            jsonArray = (JSONArray) parser.parse(reader);

            Iterator<JSONObject> it = jsonArray.iterator();
            while (it.hasNext()) {

                JSONObject obj = it.next();
                if (obj.get("role").toString().equals("gymUser") && obj.get("group").toString().equals(this.trainer.getGroup())) {

                    arrayJSon.add(obj.get("username").toString());
                }
            }

            for (String s : arrayJSon) {

                textArea.append(s + "\n");
            }

        } catch (IOException h) {
            h.printStackTrace();

        } catch (ParseException h) {
            h.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == editWP) {

            editFrame.setSize(1280, 640);
            editFrame.setVisible(true);
            editFrame.setLayout(null);
            editFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            workoutText.setBounds(200, 300, 200, 300);
            editFrame.add(saveButton);
            editFrame.add(workoutText);

            try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
                jsonArray = (JSONArray) parser.parse(reader);

            } catch (IOException h) {
                h.printStackTrace();
            } catch (ParseException h) {
                h.printStackTrace();
            }

            int i = table.getSelectedRow();

            String aux = model.getValueAt(i, 0).toString();

            Iterator<JSONObject> it = jsonArray.iterator();
            while (it.hasNext()) {

                JSONObject newObj = it.next();

                if (newObj.get("username").toString().equals(aux)) {

                    workoutText.setText(newObj.get("exercises").toString());
                }


            }

        }

        if (clicked == addUser) {
            addFrame.setSize(1280, 640);
            addFrame.setVisible(true);
            addFrame.setLayout(null);
            addFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            addUser2.setBounds(300, 300, 150, 25);
            addFrame.add(addUser2);
            addFrame.add(pane2);
            addUser2.addActionListener(this);

        }
        if(clicked == removeUser)
        {
            // i = the index of the selected row

            int i = table.getSelectedRow();
            if (i >= 0)
            // remove a row from jtable
            {
                try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
                    jsonArray = (JSONArray) parser.parse(reader);

                } catch (IOException h) {
                    h.printStackTrace();
                } catch (ParseException h) {
                    h.printStackTrace();
                }


                JSONArray auxJS = new JSONArray();

                Iterator<JSONObject> it = jsonArray.iterator();
                while (it.hasNext()) {

                    JSONObject newObj = it.next();

                    if (newObj.get("username").toString().equals(model.getValueAt(i, 0).toString())) {

                        JSONObject auxObj = new JSONObject();

                        auxObj.put("exercises", "unassigned");
                        auxObj.put("username", newObj.get("username"));
                        auxObj.put("group", "unassigned");
                        auxObj.put("role", "gymUser");
                        auxObj.put("password", newObj.get("password"));
                        auxObj.put("membershipType", newObj.get("membershipType"));
                        auxJS.add(auxObj);
                        Object row[] = new Object[1];
                        row[0]=newObj.get("username");
                        model2.addRow(row);

                        // model2.removeRow(table2.getSelectedRow());


                    } else {

                        auxJS.add(newObj);
                    }
                    try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                        file.write(auxJS.toJSONString());
                        file.flush();
                    } catch (IOException h) {
                        h.printStackTrace();
                    }


                }
                model.removeRow(i);
            }
            else{
                System.out.println("Error");
            }

        }

        if(clicked == saveButton){

            try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
                jsonArray = (JSONArray) parser.parse(reader);

            } catch (IOException h) {
                h.printStackTrace();
            } catch (ParseException h) {
                h.printStackTrace();
            }

            try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
                jsonArray = (JSONArray) parser.parse(reader);

            } catch (IOException h) {
                h.printStackTrace();
            } catch (ParseException h) {
                h.printStackTrace();
            }

            if(table.getSelectedRow() != -1) {

                String name = model.getValueAt(table.getSelectedRow(), 0).toString();

                Iterator<JSONObject> it = jsonArray.iterator();

                JSONArray auxArray = new JSONArray();

                String aux = workoutText.getText();

                while (it.hasNext()) {

                    JSONObject newObj = it.next();

                    if (newObj.get("username").toString().equals(name)) {

                        JSONObject auxObj = new JSONObject();

                        auxObj.put("exercises", aux);
                        auxObj.put("username", newObj.get("username"));
                        auxObj.put("group", newObj.get("group"));
                        auxObj.put("role", "gymUser");
                        auxObj.put("password", newObj.get("password"));
                        auxObj.put("membershipType", newObj.get("membershipType"));
                        auxObj.put("exercises", workoutText.getText());
                        auxArray.add(auxObj);

                    } else {

                        auxArray.add(newObj);
                    }
                }
                try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                    file.write(auxArray.toJSONString());
                    file.flush();
                } catch (IOException h) {
                    h.printStackTrace();
                }
            }
        }

        if (clicked == addUser2) {

            try (Reader reader = new FileReader("src/main/java/Resources/users.json")) {
                jsonArray = (JSONArray) parser.parse(reader);

            } catch (IOException h) {
                h.printStackTrace();
            } catch (ParseException h) {
                h.printStackTrace();
            }


            JSONArray auxJS = new JSONArray();
            Iterator<JSONObject> it = jsonArray.iterator();
            JSONObject newObj;
            int i = table2.getSelectedRow();
            String aux = model2.getValueAt(i,0).toString();
            if(i >= 0) {
                while (it.hasNext()) {

                    newObj = it.next();


                    if (newObj.get("username").toString().equals(aux)){

                        JSONObject auxObj = new JSONObject();

                        auxObj.put("exercises", "unassigned");
                        auxObj.put("username", newObj.get("username"));
                        auxObj.put("group", trainer.getGroup());
                        auxObj.put("role", "gymUser");
                        auxObj.put("password", newObj.get("password"));
                        auxObj.put("membershipType", newObj.get("membershipType"));
                        auxJS.add(auxObj);
                        Object row[] = new Object[1];
                        row[0] = newObj.get("username");
                        model2.removeRow(i);
                        model.addRow(row);
                        System.out.println("Your group is " + trainer.getGroup());

                    } else {

                        auxJS.add(newObj);
                    }

                }


                System.out.print(auxJS);

                try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                    file.write(auxJS.toJSONString());
                    file.flush();
                } catch (IOException h) {
                    h.printStackTrace();
                }
            }
            else System.out.print("error");
            /*String aux = "unassigned";
            Iterator<JSONObject> iti = jsonArray.iterator();
            Object[] row = new Object[1];
            while (iti.hasNext()) {
                JSONObject obj = iti.next();
                if (obj.get("role").toString().equals("gymUser") && obj.get("group").toString().equals(aux) && !obj.get("username").toString().equals(aux1)){





                    row[0] = obj.get("username").toString();
                    model2.addRow(row);

                }


            }*/
        }
    }
}
