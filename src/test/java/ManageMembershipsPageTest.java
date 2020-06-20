import MainApp.ManageMembershipsPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManageMembershipsPageTest {
    private ManageMembershipsPage manage;

    @Test
    public void addMembershipTest()
    {
        manage = new ManageMembershipsPage("admin");
        manage.setTextType("10 months");
        manage.setTextPrice("11000$");
        manage.addMembership();
        assertEquals("Added",manage.getMessage());

    }
    @Test
    public void getTypeTest()
    {
        manage= new ManageMembershipsPage("admin");
        manage.setTextType("10 months");
       // manage.setTextPrice("11000$");
        assertEquals("type",manage.getMessage());
        manage.getTextType();
        assertEquals("type",manage.getMessage());
        manage.setTextPrice("11000$");
        assertEquals("price",manage.getMessage());
        manage.getTextPrice();
        assertEquals("price",manage.getMessage());

    }

}
