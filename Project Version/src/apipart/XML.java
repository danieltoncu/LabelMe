package apipart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML {
	Document doc;
	public void createDocument() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();
			// root element
			Element rootElement = doc.createElement("categories");
			doc.appendChild(rootElement);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Categories.xml"));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addCategory(String name) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse("Categories.xml");

			Node rootElement = doc.getFirstChild();

			Element category = doc.createElement("category");
			category.setAttribute("name",name);
			rootElement.appendChild(category);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Categories.xml"));
			transformer.transform(source, result);

			//System.out.println("Categorie adaugata");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}

	}

	public void addMessage(String category,String text){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse("Categories.xml");

			Node rootElement = doc.getFirstChild();

			NodeList list = rootElement.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				Node node = list.item(i);

				NamedNodeMap attr = node.getAttributes();
				Node nodeAttr = attr.getNamedItem("name");
				//System.out.print(nodeAttr.getNodeValue());
				if(nodeAttr.getNodeValue().equalsIgnoreCase(category)){
					Element textNode = doc.createElement("Message");
					textNode.setTextContent(text);
					node.appendChild(textNode);
				}

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Categories.xml"));
			transformer.transform(source, result);

			//System.out.println("Mesaj adaugat");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	Map<String,ArrayList<String>> getMessagesForCategory (){
		Map<String,ArrayList<String>> map= new HashMap<>();
		ArrayList<String> mesaje = new ArrayList<>();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse("Categories.xml");

			Node rootElement = doc.getFirstChild();
			
			NodeList list = rootElement.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {

				Node node = list.item(i);

				NamedNodeMap attr = node.getAttributes();
				Node nodeAttr = attr.getNamedItem("name");
				String categoryName=nodeAttr.getNodeValue();
			    NodeList listaMesaje= node.getChildNodes();
			    for(int j=0;j<listaMesaje.getLength();j++){
			    	Node mesaj = listaMesaje.item(j);
			    	mesaje.add(mesaj.getTextContent());
			    }
			    map.put(categoryName, mesaje);
			    mesaje = new ArrayList<>();
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
		return map;
	}


}
