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
		File file = new File("XML2WH85ZH.xml");		
		
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

					Node node4 = elem.getElementsByTagName("szulev").item(0);
					String year = node4.getTextContent();
	
					System.out.printf("User id = %s%n", uid);
					System.out.printf("Firstname = %s%n", fname);
					System.out.printf("Lastname = %s%n", lname);
					System.out.printf("Nation = %s%n", nation);
					System.out.printf("Born in = %s%n", year);								
				}
			}						
		}

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			
			//Ha egyesülethez érünk
			if(node.getNodeName() == "egyesulet"){				
				if(!node.getNodeName().equals("#text")) {
					System.out.println("\n");
					System.out.println("Current element: " + node.getNodeName());
				}
				//Egyesületek adatainak kiírása
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					
					String uid = elem.getAttribute("E_ID");
	
					Node node1 = elem.getElementsByTagName("iranyitoszam").item(0);
					String irsz = node1.getTextContent();
	
					Node node2 = elem.getElementsByTagName("telepules").item(0);
					String tel = node2.getTextContent();
	
					Node node3 = elem.getElementsByTagName("utca").item(0);
					String utca = node3.getTextContent();

					Node node4 = elem.getElementsByTagName("hazszam").item(0);
					String hszam = node4.getTextContent();

					Node node5 = elem.getElementsByTagName("nev").item(0);
					String name = node5.getTextContent();
	
					System.out.printf("User id = %s%n", uid);
					System.out.printf("Iranyitoszam = %s%n", irsz);
					System.out.printf("Telepules = %s%n", tel);
					System.out.printf("Utca = %s%n", utca);
					System.out.printf("Hazszam = %s%n", hszam);
					System.out.printf("Name = %s%n", name);								
				}
			}						
		}
	}

	

}
