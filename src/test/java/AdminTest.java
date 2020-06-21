import AbstractTypes.Administrator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdminTest {
    Administrator admin;

    @Test
    public void accessUrlTest()
    {
        admin = new Administrator("admin");
        admin.orderEquipment();
        assertEquals(true,admin.getState());
    }
}
