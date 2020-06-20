import AbstractTypes.Trainer;
import MainApp.TrainerPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SendSuggestionToAdminTest {
    private TrainerPage trainerPage;

    @Test
    public void sendSuggestionTest()
    {
        trainerPage = new TrainerPage(new Trainer("Daniel","1"));
        trainerPage.setSuggestionText("mai multe echipamente");
        trainerPage.sendSuggestionToAdmin();
        assertEquals("Added",trainerPage.getMessage());
    }

    @Test
    public void getSuggestionTextTest()
    {
        trainerPage = new TrainerPage(new Trainer("Daniel","1"));
        trainerPage.setSuggestionText("mai multe echipamente");
        assertEquals("set",trainerPage.getMessage());
        trainerPage.getSuggestionText();
        assertEquals("get",trainerPage.getMessage());
    }
}
