import AbstractTypes.Trainer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrainerTest {
    private Trainer trainer;
    @Test
    public void getSetGroup()
    {
        trainer = new Trainer("abc","1");
        trainer.setGroup("2");
        assertEquals("2",trainer.getGroup());

    }
}
