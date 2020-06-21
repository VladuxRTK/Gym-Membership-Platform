package AuxiliaryStuff;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JSONReader {
    private static boolean read=false;
    private static boolean write = false;
    public static JSONArray readJSON(String path, JSONParser parser)
    {
        JSONArray jsonArray = new JSONArray();
        try (Reader reader = new FileReader(path)) {
            jsonArray = (JSONArray) parser.parse(reader);
            read = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
    public boolean getReadState()
    {
        return read;
    }

    public boolean getWriteState()
    {
        return write;
    }

    public static void writeJSON(String path,JSONArray array)
    {

        try (FileWriter file = new FileWriter(path)) {
            file.write(array.toJSONString());
            file.flush();
            write = true;
        } catch (IOException h) {
            h.printStackTrace();
        }
    }
}
