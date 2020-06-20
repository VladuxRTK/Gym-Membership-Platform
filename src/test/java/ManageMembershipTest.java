import AbstractTypes.GymUser;
import AuxiliaryStuff.JSONReader;
import MainApp.ManageMembership;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManageMembershipTest {
    private ManageMembership membership;

    @Test
    public void selectMembershipTest()
    {

        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        GymUser gymUser = new GymUser("zer","dfd");
        membership = new ManageMembership(gymUser);
        membership.setName("10 months");
        membership.selectMembership();
        assertEquals("selected",membership.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);
    }

    @Test
    public void setGetNameTest()
    {


        GymUser gymUser = new GymUser("zer","dfd");
        membership = new ManageMembership(gymUser);
        membership.setName("10 months");

        assertEquals("10 months",membership.getName());

    }
    @Test
    public void deleteMembershipTest()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        GymUser gymUser = new GymUser("zer","dfd");
        membership = new ManageMembership(gymUser);
        membership.deleteMembership();
        assertEquals("deleted",membership.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);
    }
}
