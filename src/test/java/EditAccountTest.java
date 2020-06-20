import AbstractTypes.GymUser;
import AuxiliaryStuff.JSONReader;
import MainApp.EditAccount;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditAccountTest {
    private EditAccount account;

    @Test
    public void changeAccountTest()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        GymUser user =  new GymUser("c","c");
        account = new EditAccount(user);
        account.setPassword("g");
        account.setRepeatPassword("g");
        account.setUsername("g");
        account.changeAccountDetails();
        assertEquals("changed",account.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);

    }
    @Test
    public void getSetPasswordTest()
    {
        GymUser user =  new GymUser("c","c");
        account = new EditAccount(user);
        account.setPassword("g");
        assertEquals("password",account.getMessage());
        account.setRepeatPassword("g");
        assertEquals("repeat",account.getMessage());
        account.getPassword();
        assertEquals("password",account.getMessage());
        account.getRepeatPassword();
        assertEquals("repeat",account.getMessage());

    }
    @Test
    public void getSetUsernameTest()
    {
        GymUser user =  new GymUser("c","c");
        account = new EditAccount(user);

        account.setUsername("g");
        assertEquals("username",account.getMessage());
        account.getUsername();
        assertEquals("username",account.getMessage());

    }

}
