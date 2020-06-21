package AbstractTypes;
import java.awt.*;
import java.net.*;

public class Administrator extends User {
	private boolean accessed;
	public Administrator(String username)
	{
		this.username = username;
	}
	public void orderEquipment() {
		try {
			  Desktop desktop = java.awt.Desktop.getDesktop();
			  URI oURL = new URI("https://www.decathlon.ro/");
			  desktop.browse(oURL);
			  accessed = true;
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}
	public boolean getState()
	{
		return accessed;
	}

}
