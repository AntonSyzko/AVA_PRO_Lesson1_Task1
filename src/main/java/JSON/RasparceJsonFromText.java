package JSON;

import com.google.gson.*;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Ыг on 19.01.2016.
 */
public class RasparceJsonFromText {

    public static void main(String[] args) {


         String s = getAll();
        System.out.println(s.toString());


    }

    public static String getAll(){
        StringBuilder sb = new StringBuilder();
        JsonParser parser = new JsonParser();
        try{
            Object obj = parser.parse(new FileReader("C:\\Users\\Ыг\\Desktop\\JAVA\\Random\\src\\main\\java\\JSON\\jsonfile.txt"));
            JsonObject jsobj = (JsonObject) obj;
          // String name = String.valueOf(jsobj.get("jsa"));
           JsonArray jsa = (JsonArray) jsobj.get("phones");
         //  sb.append(jsobj.toString());
            //sb.append(name);
           Iterator it = jsa.iterator();
           while (it.hasNext()){
                sb.append(it.next() + "  ");
           }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }









    public  static StringBuilder  rasparceFromFile(File f)  {
        StringBuilder sb = new StringBuilder();
        String lineSep = System.getProperty("line.separator");
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(f);
            br = new BufferedReader(fr);
            int lien = 0;
            while((lien = br.read())!= -1){
               sb.append(br.readLine());
                sb.append(lineSep);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb;
    }
}
