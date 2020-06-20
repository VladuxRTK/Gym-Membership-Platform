import AuxiliaryStuff.JSONReader;
import MainApp.ManageMembershipsPage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManageMembershipsPageTest {
    private ManageMembershipsPage manage;

    @Test
    public void addMembershipTest()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/memberships.json",parser);
        manage = new ManageMembershipsPage("admin");
        manage.setTextType("10 months");
        manage.setTextPrice("11000$");
        manage.addMembership();
        assertEquals("Added",manage.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/memberships.json",jsonArray);

    }
    @Test
    public void getTypeTest()
    {
        manage= new ManageMembershipsPage("admin");
        manage.setTextType("10 months");
       // manage.setTextPrice("11000$");
        assertEquals("10 months",manage.getMessage());
        manage.getTextType();
        assertEquals("type",manage.getMessage());
        manage.setTextPrice("11000$");
        assertEquals("price",manage.getMessage());
        manage.getTextPrice();
        assertEquals("price",manage.getMessage());
        mamna

    }

    public void deleteMembershipTest()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/memberships.json",parser);
        manage = new ManageMembershipsPage("admin");
        manage.setType("4 months");
        manage.deleteMembership();
        assertEquals("deleted",manage.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/memberships.json",jsonArray);

    }

    @Test
    public void updateMembershipTest()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/memberships.json",parser);
        manage = new ManageMembershipsPage("admin");
        manage.setType("1 months");
        manage.setTextPrice("4000$");
        manage.setTextType("5 months");
        manage.update();
        assertEquals("updated",manage.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/memberships.json",jsonArray);
    }


}
