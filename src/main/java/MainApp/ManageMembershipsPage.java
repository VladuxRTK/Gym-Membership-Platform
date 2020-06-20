package MainApp;
import org.apache.commons.math3.ml.neuralnet.UpdateAction;
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
import java.util.Iterator;
public class ManageMembershipsPage extends JFrame{
	
	private JSONParser parser;
	private JSONArray jsonArray;
	private DefaultTableModel model;
	private JTable table;
	private Object[] row;
	private JTextField textType;
	private JTextField textPrice;
	private String message;
	private String type;
	public ManageMembershipsPage(String name)
	{
	    super(name);
		this.setSize(600,600);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		parser=new JSONParser();
	    
		try (Reader reader = new FileReader("src/main/java/Resources/memberships.json")) {
		    	jsonArray = (JSONArray)parser.parse(reader);
		    	
		}catch (IOException h) {
			h.printStackTrace();
			} catch (ParseException h) {
			h.printStackTrace();
			}
		
		   // create JFrame and JTable
        JFrame frame = new JFrame();
		table = new JTable();
        
        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Type","Price"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        // create JTextFields
      textType = new JTextField();
      textPrice = new JTextField();
       
        
        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");     
        
        textType.setBounds(20, 220, 100, 25);
        textPrice.setBounds(20, 250, 100, 25);
       
        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
       // frame.setLayout(null);
        
        this.add(pane);
        
        // add JTextFields to the jframe
        this.add(textType);
        this.add(textPrice);
      
    
        // add JButtons to the jframe
        this.add(btnAdd);
        this.add(btnDelete);
        this.add(btnUpdate);
        
        // create an array of objects to set the row data
         row = new Object[2];
        
        Iterator<JSONObject> it = jsonArray.iterator();
		while (it.hasNext()) {
		JSONObject obj = it.next();
		row[0]=obj.get("name").toString();
		row[1]=obj.get("price").toString();
		model.addRow(row);
		}
        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                addMembership();
            }

        });

        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                deleteMembership();

        }});
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            
            textType.setText(model.getValueAt(i, 0).toString());
            textPrice.setText(model.getValueAt(i, 1).toString());
           
        }
        });
        
        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = the index of the selected row
                int i = table.getSelectedRow();
                type = model.getValueAt(i,0).toString();

                if(i >= 0)
                {

                    model.setValueAt(textType.getText(), i, 0);
                    model.setValueAt(textPrice.getText(), i, 1);
                    update();

                }
                else{
                    System.out.println("Update Error");
                }
            }
        });
        getContentPane().setBackground(new Color(65, 105, 225));
        
	}
	public void setTextType(String text)
    {
        this.textType.setText(text);
        message = "type";
    }
    public void setTextPrice(String text)
    {
        this.textPrice.setText(text);
        message="price";
    }

    public String getTextType()
    {
        message = "type";
        return textType.getText();

    }
    public String getTextPrice()
    {
        message = "price";
        return textPrice.getText();
    }
    public String getMessage()
    {
        return message;
    }
	
	public void addMembership()
    {

        row[0] = textType.getText();
        row[1] = textPrice.getText();

        // add row to the model
        model.addRow(row);
        JSONObject obj = new JSONObject();
        obj.put("name", row[0]);
        obj.put("price", row[1]);

        jsonArray.add(obj);

        try (FileWriter file = new FileWriter("src/main/java/Resources/memberships.json")) {
            file.write(jsonArray.toJSONString());
            file.flush();
            message = "Added";
        }catch (IOException h) {
            h.printStackTrace();
        }

    }
    public void setType(String type)
    {
        this.type = type;
    }
    public void deleteMembership() {
        int i = table.getSelectedRow();
        type = model.getValueAt(i, 0).toString();
        if (i >= 0) {
            // remove a row from jtable



             delete();
            model.removeRow(i);

        }

                else{
        System.out.println("Delete Error");
    }
}
public void delete()
{
    JSONArray auxJSON = new JSONArray();
    Iterator<JSONObject> it = jsonArray.iterator();
    while (it.hasNext()) {
        JSONObject obj = it.next();
        if (!obj.get("name").toString().equals(type)) {
            auxJSON.add(obj);
        }


        try (FileWriter file = new FileWriter("src/main/java/Resources/memberships.json")) {
            file.write(auxJSON.toJSONString());
            file.flush();
            message="deleted";


        } catch (IOException h) {
            h.printStackTrace();
        }
    }
}
public void update()
{
    JSONArray auxJSON = new JSONArray();

    Iterator<JSONObject> it = jsonArray.iterator();
    while (it.hasNext()) {
        JSONObject obj = it.next();
        if(obj.get("name").toString().equals(type))
        {
            JSONObject auxObj = new JSONObject();
            auxObj.put("name",textType.getText());
            auxObj.put("price",textPrice.getText());
            auxJSON.add(auxObj);
        }
        else {auxJSON.add(obj);}

        try (FileWriter file = new FileWriter("src/main/java/Resources/memberships.json")) {
            file.write(auxJSON.toJSONString());
            file.flush();
            message="updated";


        } catch (IOException h) {
            h.printStackTrace();
        }
    }
}
        }




