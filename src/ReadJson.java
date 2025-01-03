import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JFrame mainFrame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JTextArea ta1;
    private JTextArea ta2;
    private JTextArea ta3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton button1;
    private JButton button2;
    private int WIDTH=800;
    private int HEIGHT=700;

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

    public ReadJson() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Pokedex");
        mainFrame.setSize(WIDTH, HEIGHT);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        ta1 = new JTextArea();
        ta2 = new JTextArea();
        ta3 = new JTextArea();
        label1 = new JLabel("Pokemon Name: ", JLabel.CENTER);
        label2 = new JLabel("Pokemon Info: ", JLabel.CENTER);
        label3 = new JLabel("Search New Pokemon: ", JLabel.CENTER);
        label4 = new JLabel("Pokemon Image: ", JLabel.CENTER);

        button1 = new JButton("Submit");
        button2 = new JButton("Reset");
        button1.setActionCommand("Submit");
        button2.setActionCommand("Reset");
        button1.addActionListener(new ButtonClickListener());
        button2.addActionListener(new ButtonClickListener());
        button1.setFont(new Font("Calibri", Font.BOLD, 30));
        button2.setFont(new Font("Calibri", Font.BOLD, 30));
        button2.setBackground(new Color(252, 22, 5));
        button1.setBackground(new Color(0, 201, 27));
        button1.setOpaque(true);
        button2.setOpaque(true);
        button1.setBorderPainted(false);
        button2.setBorderPainted(false);

        mainFrame.setLayout(new GridLayout(1, 1));
        panel2.setLayout(new GridLayout(2, 1));
        panel3.setLayout(new GridLayout(1, 2));
        panel4.setLayout(new BorderLayout());
        panel5.setLayout(new GridLayout(2,1));
        panel6.setLayout(new BorderLayout());


        //mainFrame.add(panel1);
        //panel1.add(label4);
        panel2.add(panel3);
        panel3.add(panel4);
        panel3.add(panel5);
        panel5.add(button1);
        panel5.add(button2);
        //panel5.add(label2, BorderLayout.NORTH);
        mainFrame.add(panel2);
        panel2.add(panel6);
        panel6.add(label2, BorderLayout.NORTH);
        panel6.add(ta2, BorderLayout.CENTER);
        panel4.add(label1, BorderLayout.NORTH);
        panel4.add(ta1, BorderLayout.CENTER);


        mainFrame.setVisible(true);


    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";

        //how to get submit button to be action command

        String name1 = ta1.getText();


            try {

                URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + ta1.getText());
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
                    totlaJson += output;
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

                String name = (String) jsonObject.get("name");
                Long weight = (Long) jsonObject.get("weight");
                Long height = (Long) jsonObject.get("height");


                String eye = (String) jsonObject.get("eye_color");
                String birth = (String) jsonObject.get("birth_year");
                System.out.println();

                ta2.append("Pokemon name: " + name);
                ta2.append("\n");
                ta2.append("Pokemon weight: " + weight);
                ta2.append("\n");
                ta2.append("Pokemon height: " + height);
                ta2.append("\n");



                org.json.simple.JSONArray ab = (org.json.simple.JSONArray) jsonObject.get("abilities");
                int n = ab.size(); //(msg).length();a
                for (int i = 0; i < n; ++i) {
                    JSONObject test = (JSONObject) ab.get(i);
                    JSONObject ability = (JSONObject) test.get("ability");
                    ta2.append("Ability " + (i+1) + ": " + ability.get("name"));
                    ta2.append("\n");
                }


                org.json.simple.JSONArray forms = (org.json.simple.JSONArray) jsonObject.get("forms");
                int f = forms.size();
                for (int i = 0; i < f; i = i + 1) {
                    JSONObject test2 = (JSONObject) forms.get(i);
                    String names = (String) test2.get("name");
                    //String links = (String) test2.get("url");
                    ta2.append("Form " + (i+1) + ": " + names);
                    ta2.append("\n");
                   // ta2.append("Form link: " + links);
                   // ta2.append("\n");
                }

                org.json.simple.JSONArray type = (org.json.simple.JSONArray) jsonObject.get("types");
                int t = type.size();
                for (int i = 0; i < t; i = i + 1) {
                    JSONObject test3 = (JSONObject) type.get(i);
                    JSONObject types = (JSONObject) test3.get("type");
                    String names = (String) types.get("name");
                    ta2.append("Type " + (i+1) + ": " + names);
                    ta2.append("\n");
                }

                org.json.simple.JSONArray move = (org.json.simple.JSONArray) jsonObject.get("moves");
                int m = type.size();
                for (int i = 0; i < m; i = i + 1) {
                    JSONObject test4 = (JSONObject) move.get(i);
                    JSONObject moves = (JSONObject) test4.get("move");
                    String movesy = (String) moves.get("name");
                    ta2.append("Move " + (i+1) + ": " + movesy);
                    ta2.append("\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }



    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent f) {
            String command = f.getActionCommand();


            if (command.equals("Submit")) {
                try {
                    pull();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if(command.equals("Reset")){
                ta2.setText("");
                ta1.setText("");
            }


        }
    }
}