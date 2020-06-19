package MainApp;
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
        JTable table = new JTable(); 
        
        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Type","Price"};
        DefaultTableModel model = new DefaultTableModel();
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
        JTextField textType = new JTextField();
        JTextField textPrice = new JTextField();
       
        
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
        Object[] row = new Object[2];
        
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
            }catch (IOException h) {
        	    h.printStackTrace();
    	    }
            }
        });
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable

                    String aux1 = model.getValueAt(i,0).toString();
                    JSONArray auxJSON = new JSONArray();
                    Iterator<JSONObject> it = jsonArray.iterator();
                    while (it.hasNext()) {
                        JSONObject obj = it.next();
                        if(!obj.get("name").toString().equals(aux1)) {
                            auxJSON.add(obj);
                        }



                        try (FileWriter file = new FileWriter("src/main/java/Resources/memberships.json")) {
                            file.write(auxJSON.toJSONString());
                            file.flush();


                        } catch (IOException h) {
                            h.printStackTrace();
                        }
                    }
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
        
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
                
                if(i >= 0) 
                {
                   String aux1 = model.getValueAt(i,0).toString();
                   JSONArray auxJSON = new JSONArray();
                   model.setValueAt(textType.getText(), i, 0);
                   model.setValueAt(textPrice.getText(), i, 1);
                   Iterator<JSONObject> it = jsonArray.iterator();
                   while (it.hasNext()) {
               		JSONObject obj = it.next();
               		if(obj.get("name").toString().equals(aux1))
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
            	    
            	    
            	    } catch (IOException h) {
            	    h.printStackTrace();
            	    }
                   }
                   
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });
        getContentPane().setBackground(new Color(65, 105, 225));
        
	}
	
	

}
