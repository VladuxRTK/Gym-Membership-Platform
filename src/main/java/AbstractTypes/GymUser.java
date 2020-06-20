package AbstractTypes;

public class GymUser extends User{
    private String group;
    public GymUser(String username,String password)
    {
        this.username = username;
        this.password=password;
    }
    public void setGroup(String group)
    {
        this.group = group;
    }
    public String getGroup(String group)
    {
        return this.group;
    }
}
