package AuxiliaryStuff;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JSONReader {
    public static JSONArray readJSON(String path, JSONParser parser)
    {
        JSONArray jsonArray = new JSONArray();
        try (Reader reader = new FileReader(path)) {
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
