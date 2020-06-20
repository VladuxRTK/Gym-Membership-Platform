import MainApp.Login;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private Login login;
    private static final String username = "test";
    private static final String password = "test";

    @Before
    public void setUp()
    {

    }

    @Test
    public void handleLoginTest1()
    {
        login = new Login();
        login.setPasswordText("test");
        login.setUsernameText("test");
        login.setRadioButton2();
        login.handleLogin();
        assertEquals("Logged in!",login.getMessageString());
    }

    public void handleLoginBadTestInfo1()
    {
        login = new Login();
        login.setPasswordText("test");
        login.setUsernameText("test");
        login.setRadioButton1();
        login.handleLogin();
        assertEquals("Account not found! Verify the information or create a new account!",login.getMessageString());

    }
    public void handleLoginBadTestInfo2()
    {
        login = new Login();
        login.setPasswordText("test");
        login.setUsernameText("test");
        login.setRadioButton3();
        login.handleLogin();
        assertEquals("Account not found! Verify the information or create a new account!",login.getMessageString());

    }
    public void handleLoginBadTestInfo1()
    {
        login = new Login();
        login.setPasswordText("v");
        login.setUsernameText("v");
        login.setRadioButton2();
        login.handleLogin();
        assertEquals("Account not found! Verify the information or create a new account!",login.getMessageString());

    }
    public void handleLoginTest2()
    {
        login = new Login();
        login.setPasswordText("admin");
        login.setUsernameText("1234");
        login.setRadioButton1();
        login.handleLogin();
        assertEquals("Logged in!",login.getMessageString());

    }
    public void handleLoginTest3()
    {
        login = new Login();
        login.setPasswordText("g");
        login.setUsernameText("g");
        login.setRadioButton3();
        login.handleLogin();
        assertEquals("Logged in!",login.getMessageString());
    }


}
