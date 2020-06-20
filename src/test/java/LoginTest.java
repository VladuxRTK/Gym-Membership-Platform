import MainApp.Login;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private Login login;
    private static final String username = "test";
    private static final String password = "test";

    @Test
    public void testSetPassword()
    {
        login = new Login();

        login.setPasswordText("abc");
        assertEquals("abc",login.getMessageString());
    }
    @Test
    public void testSetUsername()
    {
        login = new Login();
        login.setUsernameText("abc");
        assertEquals("abc",login.getMessageString());
    }

    @Test
    public void testSetSelected1()
    {
        login = new Login();
        login.setRadioButton1();
        assertEquals(true,login.state());

    }
    @Test
    public void testSetSelected2()
    {
        login = new Login();
        login.setRadioButton2();
        assertEquals(true,login.state());

    }
    @Test
    public void testSetSelected3()
    {
        login = new Login();
        login.setRadioButton3();
        assertEquals(true,login.state());

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

    @Test
    public void handleLoginBadTestInfo1()
    {
        login = new Login();
        login.setPasswordText("test");
        login.setUsernameText("test");
        login.setRadioButton1();
        login.handleLogin();
        assertEquals("Account not found! Verify the information or create a new account!",login.getMessageString());

    }
    @Test
    public void handleLoginBadTestInfo2()
    {
        login = new Login();
        login.setPasswordText("test");
        login.setUsernameText("test");
        login.setRadioButton3();
        login.handleLogin();
        assertEquals("Account not found! Verify the information or create a new account!",login.getMessageString());

    }
    @Test
    public void handleLoginBadTestInfo3()
    {
        login = new Login();
        login.setPasswordText("v");
        login.setUsernameText("v");
        login.setRadioButton2();
        login.handleLogin();
        assertEquals("Account not found! Verify the information or create a new account!",login.getMessageString());

    }
    @Test
    public void handleLoginTest2()
    {
        login = new Login();
        login.setPasswordText("1234");
        login.setUsernameText("admin");
        login.setRadioButton1();
        login.handleLogin();
        assertEquals("Logged in!",login.getMessageString());

    }
    @Test
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
