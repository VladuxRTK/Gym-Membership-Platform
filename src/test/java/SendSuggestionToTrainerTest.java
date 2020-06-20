import AbstractTypes.GymUser;
import AuxiliaryStuff.JSONReader;
import MainApp.GymUserPage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SendSuggestionToTrainerTest {
    private GymUserPage gymUserPage;

    @Test
    public void sendSuggestionTest()
    {
        GymUser gym = new GymUser("zer","test");

        GymUserPage gymUserPage = new GymUserPage(gym);

        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        gymUserPage.setSuggestionText("I want a better workout");
        gymUserPage.sendSuggestion();
        assertEquals("suggestion",gymUserPage.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);
    }

    @Test
    public void setGetSuggestionTextTest()
    {
        GymUser gym = new GymUser("zer","test");

        GymUserPage gymUserPage = new GymUserPage(gym);


        gymUserPage.setSuggestionText("I want a better workout");
        assertEquals("I want a better workout",gymUserPage.getSuggestionText());
    }
}

