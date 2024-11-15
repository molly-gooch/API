import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

// Program for print data in JSON format.
public class ReadJson {

    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma"); //key then value
        file.put("Roll No.", 1704310046);
        file.put("Tution Fees", 65400);


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        ReadJson readingIsWhat = new ReadJson();

    }

    public ReadJson(){
        try {
            pull();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public  void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            String name = (String)jsonObject.get("name");


            String eye = (String)jsonObject.get("eye_color");
            String birth = (String)jsonObject.get("birth_year");
            System.out.println();

            org.json.simple.JSONArray ab = (org.json.simple.JSONArray) jsonObject.get("abilities");
            int n =   ab.size(); //(msg).length();a
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) ab.get(i);
                JSONObject ability = (JSONObject)test.get("ability");
                System.out.println("Ability name: " + ability.get("name"));
            }

            System.out.println("Character Name: " + name);

            org.json.simple.JSONArray forms = (org.json.simple.JSONArray) jsonObject.get("forms");
            int f = forms.size();
            for(int i=0;i<f;i=i+1){
                JSONObject test2 = (JSONObject) forms.get(i);
                String names = (String) test2.get("name");
                String links = (String) test2.get("url");
                System.out.println("Form name: " + names);
                System.out.println("Form link: " + links);
//                
            }


//            org.json.simple.JSONArray starships = (org.json.simple.JSONArray) jsonObject.get("");
//            int b = starships.size();
//            for(int w = 0; w<b;w++){
//                String test2 = (String)starships.get(w);
//                System.out.println(test2);
//            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}


