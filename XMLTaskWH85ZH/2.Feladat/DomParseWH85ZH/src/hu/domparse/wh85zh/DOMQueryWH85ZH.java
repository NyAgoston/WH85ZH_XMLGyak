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

public class DOMQueryWH85ZH {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//Forrás file 
		File file = new File("XML2WH85ZH.xml");

		//csapatok álltal szerzett összpontszám
		int sum = 0;
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		 
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		//Gyökér elem
		System.out.print("Root element: ");
        System.out.println(doc.getDocumentElement().getNodeName());
        //Tabella mentése
        
         
        System.out.println("----------------------------");
         
        // összeszámolja a csapatok összpontszámát
		NodeList nList = doc.getDocumentElement().getElementsByTagName("tabella");

        for (int i = 0; i < nList.getLength(); i++) {
            NodeList query = nList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("pontok")){
                    sum += Integer.parseInt(query.item(j).getTextContent());
                }
            }
        }

        
		//összpontok
		System.out.println("Összes pont: "+ sum);
		System.out.println("----------------------------");
		

		//Játékosok akik idősebbek 30 nál
		NodeList nList2 = doc.getDocumentElement().getElementsByTagName("jatekos");

        for (int i = 0; i < nList2.getLength(); i++) {
            NodeList query = nList2.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
				Node node2 = query.item(j);
                if (query.item(j).getNodeName().equals("kor") && Integer.parseInt(query.item(j).getTextContent()) >= 30){
                    NodeList nList0 = nList2.item(i).getChildNodes();
					for (int k = 0; k < nList0.getLength(); k++) {
						Node node3 = nList0.item(k);							
						if (node3.getNodeType() == Node.ELEMENT_NODE) {																						
							System.out.println(node3.getNodeName()+" : "+node3.getTextContent());					
						}						
					}	
					System.out.println();					
                }								
            }
        }

		System.out.println("----------------------------");
		//Bírók kilistázása
		NodeList nList3 = doc.getDocumentElement().getElementsByTagName("biro");

        for (int i = 0; i < nList3.getLength(); i++) {
            NodeList query = nList3.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
				Node node2 = query.item(j);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println(node2.getNodeName()+" : "+node2.getTextContent());					
				}					              							
            }
			System.out.println();
        }

		System.out.println("----------------------------");
		//Játékosok adatai akiknek a fizetése kevesebb mint 100
		NodeList nList4 = doc.getDocumentElement().getElementsByTagName("jatekos");
        for (int i = 0; i < nList4.getLength(); i++) {
            NodeList query = nList4.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
				Node node2 = query.item(j);
                if (node2.getNodeName().equals("fizetes") && Integer.parseInt(node2.getTextContent()) <= 100){
                    NodeList nList0 = nList4.item(i).getChildNodes();
					for (int k = 0; k < nList0.getLength(); k++) {
						Node node3 = nList0.item(k);							
						if (node3.getNodeType() == Node.ELEMENT_NODE) {																						
							System.out.println(node3.getNodeName()+" : "+node3.getTextContent());					
						}						
					}	
					System.out.println();
                }																
            }
        }

		System.out.println("----------------------------");
		//Spanyol nemzetiségű edző

		NodeList nList5 = doc.getDocumentElement().getElementsByTagName("edzo");
        for (int i = 0; i < nList5.getLength(); i++) {
            NodeList query = nList5.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
				Node node2 = query.item(j);
                if (node2.getNodeName().equals("nemzetiseg") && node2.getTextContent().equals("Spanyol")){
                    NodeList nList0 = nList5.item(i).getChildNodes();
					for (int k = 0; k < nList0.getLength(); k++) {
						Node node3 = nList0.item(k);							
						if (node3.getNodeType() == Node.ELEMENT_NODE) {																						
							System.out.println(node3.getNodeName()+" : "+node3.getTextContent());					
						}						
					}	
					System.out.println();
                }																
            }
        }
	}
}
