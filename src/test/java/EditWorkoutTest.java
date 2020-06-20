import AbstractTypes.GymUser;
import AbstractTypes.Trainer;
import AuxiliaryStuff.JSONReader;
import MainApp.EditUserGroup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditWorkoutTest {
    private EditUserGroup edit;

    @Test
    public void editWorkoutTest()
    {
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);

        Trainer trainer = new Trainer("test","1");
        edit = new EditUserGroup(trainer);
       // GymUser user = new GymUser("User","user");
      //  user.setGroup("200");
        edit.setWorkoutText("2*abs");
        edit.setName("adi");
        edit.editWorkout();
        assertEquals("Added workout",edit.getMessage());
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);





    }
}
