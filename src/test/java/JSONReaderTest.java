import AuxiliaryStuff.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSONReaderTest {


    @Test
    public void JSONReaderTestMethod()
    {
        JSONReader reader= new JSONReader();
        JSONArray jsonArray = new JSONArray();
        JSONParser parser =new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
        assertEquals(true,reader.getReadState());
    }

    @Test
    public void JSONWriterTestMethod()
    {
        JSONReader reader= new JSONReader();
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        jsonArray = JSONReader.readJSON("src/main/java/Resources/users.json",parser);
       /* JSONArray auxJSON = jsonArray;
        JSONObject obj = new JSONObject();
        obj.put("test","test");*/
        JSONReader.writeJSON("src/main/java/Resources/users.json",jsonArray);
        assertEquals(true,reader.getWriteState());

    }
}
