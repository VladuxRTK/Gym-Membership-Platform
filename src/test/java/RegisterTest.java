import AbstractTypes.Trainer;
import MainApp.RegisterPage;
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
        register = new RegisterPage();
        register.setRadioButton1();
        register.setPasswordText("1234");
        register.setRepeatPasswordText("1234");
        register.setUsernameText("1234");
        register.handleRegister();
        assertEquals("Account created successfully!",register.getMessageString());
    }

    @Test
    public void testAddTheSameUser()
    {
        register = new RegisterPage();
        register.setRadioButton1();
        register.setPasswordText("h");
        register.setRepeatPasswordText("h");
        register.setUsernameText("5");
        register.handleRegister();
        register.handleRegister();

        assertEquals("Username already taken!",register.getMessageString());
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
