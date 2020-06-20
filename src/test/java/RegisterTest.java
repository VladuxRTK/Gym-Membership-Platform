import AbstractTypes.Trainer;
import AuxiliaryStuff.JSONReader;
import MainApp.RegisterPage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterTest {

    private static final String USER_TEXT = "testUser";
    private static final String PASSWORD_TEXT = "testPassword";
    private static final String REPEAT_PASSWORD ="testPassword";
    private RegisterPage register;

    /*@Before
    public void setUp()
    {
        register = new RegisterPage();
        register.setRadioButton1();
        register.setPasswordText(PASSWORD_TEXT);
        register.setRepeatPasswordText(REPEAT_PASSWORD);
        register.setUsernameText(USER_TEXT);
    }*/

   /* @Test
    public void testGetter()
    {
        Trainer trainer = new Trainer("abc","abc");
        assertEquals("abc",trainer.getGroup());
    }*/
    @Test
    public void testHandleRegistration()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        register = new RegisterPage();
        register.setRadioButton1();
        register.setPasswordText("1234");
        register.setRepeatPasswordText("1234");
        register.setUsernameText("1234");
        register.handleRegister();
        assertEquals("Account created successfully!",register.getMessageString());
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);
    }

    @Test
    public void testAddTheSameUser()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        register = new RegisterPage();
        register.setRadioButton1();
        register.setPasswordText("h");
        register.setRepeatPasswordText("h");
        register.setUsernameText("5");
        register.handleRegister();
        register.handleRegister();

        assertEquals("Username already taken!",register.getMessageString());
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);
    }

    public void testPasswordMatch()
    {
        register = new RegisterPage();
        register.setRadioButton1();
        register.setPasswordText("c");
        register.setRepeatPasswordText("h");
        register.setUsernameText("22");
        register.handleRegister();
        register.handleRegister();

        assertEquals("Password must match!",register.getMessageString());

    }




}
