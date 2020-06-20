package MainApp;

import AbstractTypes.GymUser;
import AuxiliaryStuff.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class ManageMembership extends JFrame implements ActionListener {
    private GymUser gymUser;
    private JSONArray jsonArray;
    private JSONParser parser;
    private DefaultTableModel model;
    private JTextField textType;
    private Object[] row = new Object[2];
    private JButton btnDelete;
    private JTable table;
    private JButton btnAdd;
    private String name;
    private String message;

    public ManageMembership(GymUser gymUser) {
        this.gymUser = gymUser;
        table = new JTable();
        parser = new JSONParser();

        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Type", "Price"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);


        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(30);

        // create JTextFields
        textType = new JTextField();
        // textPrice = new JTextField();


        // create JButtons
        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        //JButton btnUpdate = new JButton("Update");

        textType.setBounds(20, 220, 100, 25);
        // textPrice.setBounds(20, 250, 100, 25);
        btnAdd.addActionListener(this);
        btnAdd.setBounds(150, 220, 100, 25);
        //btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 275, 100, 25);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        // frame.setLayout(null);
        this.setSize(600, 600);

        this.add(pane);

        // add JTextFields to the jframe
        this.add(textType);
        this.getContentPane().setBackground(new Color(65, 105, 225));
        //this.add(textPrice);
        //displayInfo();

        // add JButtons to the jframe
        this.add(btnAdd);

        this.add(btnDelete);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        displayInfo();
        // this.add(btnUpdate);

        // create an array of objects to set the row data

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == btnAdd) {
            addMembership();
            this.dispose();
            //displayInfo();
        }
        if (clicked == btnDelete) {
            deleteMembership();
            this.dispose();
        }
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getMessage()
    {
        return message;
    }

    public String getName()
    {
        return this.name;
    }

    public void displayInfo() {

        jsonArray = JSONReader.readJSON("src/main/java/Resources/memberships.json", parser);
        Iterator<JSONObject> it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject obj = it.next();
            row[0] = obj.get("name").toString();
            row[1] = obj.get("price").toString();
            model.addRow(row);
        }
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json", parser);
        it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject obj = it.next();
            if(obj.get("username").toString().equals(this.gymUser.getUsername()))
            {
                textType.setText(obj.get("membershipType").toString());
            }
        }

    }

    public void addMembership() {
        // row[0] = textType.getText();
        // row[1] = textPrice.getText();

        // add row to the model
        //System.out.println(this.gymUser.getUsername());
        int i = table.getSelectedRow();
        name = model.getValueAt(i, 0).toString();
        if (i >= 0) {
            // remove a row from jtables
            selectMembership();


            //model.removeRow(i);

       /* try (FileWriter file = new FileWriter("src/main/java/Resources/memberships.json")) {
            file.write(jsonArray.toJSONString());
            file.flush();
        }catch (IOException h) {
            h.printStackTrace();
        }*/
        }
    }

    public void deleteMembership() {
        //int i = table.getSelectedRow();
        // String aux1 = model.getValueAt(i, 0).toString();
        //if (i >= 0) {
        // remove a row from jtable


        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json", parser);
        JSONArray auxJSON = new JSONArray();
        JSONObject auxObj = new JSONObject();
        JSONObject obj;
        System.out.println(this.gymUser.getUsername());
        Iterator<JSONObject> it = jsonArray.iterator();
        while (it.hasNext()) {
            obj = it.next();
            if (obj.get("username").toString().equals(this.gymUser.getUsername())) {

                auxObj.put("username", obj.get("username"));
                auxObj.put("password", obj.get("password"));
                auxObj.put("exercises", obj.get("exercises"));
                auxObj.put("role", "gymUser");
                auxObj.put("membershipType", "unassigned");

                auxJSON.add(auxObj);
                message = "deleted";

            } else {
                auxJSON.add(obj);
            }


            try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                file.write(auxJSON.toJSONString());
                file.flush();



            } catch (IOException h) {
                h.printStackTrace();
            }
        }
    }
    public void selectMembership()
    {
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json", parser);
        JSONArray auxJSON = new JSONArray();
        JSONObject auxObj = new JSONObject();
        JSONObject obj;
        System.out.println(this.gymUser.getUsername());
        Iterator<JSONObject> it = jsonArray.iterator();
        while (it.hasNext()) {
            obj = it.next();
            if (obj.get("username").toString().equals(this.gymUser.getUsername())) {

                auxObj.put("username", obj.get("username"));
                auxObj.put("password", obj.get("password"));
                auxObj.put("exercises", obj.get("exercises"));
                auxObj.put("role", "gymUser");
                auxObj.put("membershipType", name);

                auxJSON.add(auxObj);
                message = "selected";
            } else {
                auxJSON.add(obj);
            }


            try (FileWriter file = new FileWriter("src/main/java/Resources/users.json")) {
                file.write(auxJSON.toJSONString());
                file.flush();


            } catch (IOException h) {
                h.printStackTrace();
            }
        }
    }

}

