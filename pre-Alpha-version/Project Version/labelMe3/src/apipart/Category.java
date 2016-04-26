package apipart;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Category {
	public void define(String name, String filename) {
		File file = new File(filename);
		File model = new File("../resources/api/model.train");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
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
				toput=name+"  "+line;
				for(int i=1;i<=30;i++){
					writer.write(line+"\n");
				}
				writer.flush();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			writer.write("This\n is\n an\n example\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Creates a FileReader Object
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char[] a = new char[50];
		try {
			fr.read(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // reads the content to the array
		for (char c : a)
			System.out.print(c); // prints the characters one by one
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
