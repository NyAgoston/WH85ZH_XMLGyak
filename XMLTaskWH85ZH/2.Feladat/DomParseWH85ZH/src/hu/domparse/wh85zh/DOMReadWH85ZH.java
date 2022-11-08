package hu.domparse.wh85zh;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadWH85ZH {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//Forrás file 
		File file = new File("XMLWH85ZH.xml");		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(file);
		
		doc.getDocumentElement().normalize();
		//Gyökérelem
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		//Gyerekelemek lementése
		NodeList nList = (NodeList) doc.getDocumentElement();
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			
			
			//Ha jatekoshoz erünk
			if(node.getNodeName() == "jatekos"){
				if(!node.getNodeName().equals("#text")) {
					System.out.println("\n");
					System.out.println("Current element: " + node.getNodeName());
				}
				//Játékosok adatainak kiírása
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element elem = (Element) node;
	
					String uid = elem.getAttribute("J_ID");
	
					Node node1 = elem.getElementsByTagName("vezeteknev").item(0);
					String fname = node1.getTextContent();
	
					Node node2 = elem.getElementsByTagName("keresztnev").item(0);
					String lname = node2.getTextContent();
	
					Node node3 = elem.getElementsByTagName("nemzetiseg").item(0);
					String nation = node3.getTextContent();
	
					System.out.printf("User id = %s%n", uid);
					System.out.printf("Firstname = %s%n", fname);
					System.out.printf("Lastname = %s%n", lname);
					System.out.printf("Profession = %s%n", nation);								
				}
			}
			
			
		}
	}

}
