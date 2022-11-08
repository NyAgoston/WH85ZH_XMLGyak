package hu.domparse.wh85zh;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyWH85ZH {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		try {
			//Forrás file 
			File inputFile = new File("XML2WH85ZH.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
	        
			//Második és harmadik egyesület mentése
			Node club1 = doc.getElementsByTagName("egyesulet").item(1);
			Node club2 = doc.getElementsByTagName("egyesulet").item(2);
			//Gyökérelem
			Node bajnoksag = doc.getFirstChild();
	        
			//Harmadik egyesület ID váltás
			NamedNodeMap attr = club2.getAttributes();
			Node nodeAttr = attr.getNamedItem("E_ID");
			nodeAttr.setTextContent("4");
	         
			//Második egyesület irányítószámának megváltoztatása
			NodeList list = club1.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					if ("iranyitoszam".equals(elem.getNodeName())) {					
						elem.setTextContent("3532");  						
					}
				}
			}
	        
			//Harmadik egyesület utcanevének megváltoztatása
			NodeList list1 = club2.getChildNodes();
			for (int i = 0; i < list1.getLength(); i++) {
				Node node1 = list1.item(i);
				if (node1.getNodeType() == Node.ELEMENT_NODE) {
					Element elem1 = (Element) node1;
					if ("utca".equals(elem1.getNodeName())) {						
						elem1.setTextContent("Gorton");  						
					}
				}
			}
	        
			//Bírók kitörlése
			NodeList childNodes = bajnoksag.getChildNodes();
			for(int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
	            
				if("biro".equals(node.getNodeName()))
					bajnoksag.removeChild(node);
			}
	        
	        //Konzolra kírás
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.println("-----------New File-----------");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}

