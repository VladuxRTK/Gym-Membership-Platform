import AbstractTypes.GymUser;
import AbstractTypes.Trainer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GymUserTest {
    private GymUser user;

    @Test
    public void getSetGroup()
    {
        user = new GymUser("abc","abc");
        user.setGroup("2");
        assertEquals("2",user.getGroup());

    }
}
