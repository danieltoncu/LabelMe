package apipart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Category {

    public void addToModel(String name, String filename) {
        File file = new File(filename);
        File model = new File("src/resources/api/model.train");
        FileWriter writer = null;
        try {
            writer = new FileWriter(model, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(reader);
        String line;
        String toput;
        try {
            while ((line = br.readLine()) != null) {
                toput = name + "  " + line;
                for (int i = 1; i <= 30; i++) {
                    writer.write(toput + "\n");
                }
                writer.flush();
                toput = "";
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
     public void addToModel(String name, ArrayList<String> keywords) {
        File model = new File("src/resources/api/model.train");
        FileWriter writer = null;
        try {
            writer = new FileWriter(model, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String toput;
        try {
            for(int i=0;i<keywords.size();i++) {
                toput = name + "  " + keywords.get(i);
                for (int j = 1; j <= 30; j++) {
                    writer.write(toput + "\n");
                }
                writer.flush();
                toput = "";
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void define(String name, String filename) {
        addToModel(name, filename);
        XML xml = new XML();
        xml.addCategory(name);
        Categorize categorize=new Categorize();
    	categorize.trainModel();
    }
    public void define(String name, ArrayList<String> keywords) {
        addToModel(name, keywords);
        XML xml = new XML();
        xml.addCategory(name);
        Categorize categorize=new Categorize();
    	categorize.trainModel();
    }
}
