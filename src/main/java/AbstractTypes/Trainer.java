package AbstractTypes;

public class Trainer extends User {

	private String group;

	public Trainer(String username,String group)
	{
		this.username = username;
		this.group = group;
	}

	public String getTrainer(){

		return this.toString();
	}

	public String getGroup(){

		return group;
	}
	public void setGroup(String group)

	{
		this.group = group;
	}



}
