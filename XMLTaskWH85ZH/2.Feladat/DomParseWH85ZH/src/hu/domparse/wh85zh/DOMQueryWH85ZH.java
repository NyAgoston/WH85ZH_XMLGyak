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
		File file = new File("XMLWH85ZH.xml");

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
        NodeList nList = doc.getElementsByTagName("tabella");
         
        System.out.println("----------------------------");
         
        //Végigfut a tabella gyerek elemein kihagyva a pontokat
        for (int i = 0; i < nList.getLength(); i++) {
        	Node node = nList.item(i);
            System.out.println("\nCurrent Element : "+node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element elem = (Element) node;
            	System.out.println("ID:"+elem.getAttribute("T_ID"));
            	NodeList nList2 = elem.getChildNodes();
            	for (int j = 0; j < nList2.getLength(); j++) {
            		Node node2 = nList2.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element elem2 = (Element) node2;
						if(!node2.getNodeName().equals("pontok")) {
							System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
						}else{
							sum += Integer.parseInt(node2.getTextContent());
						}
						NodeList nList3 = elem2.getChildNodes();
						for (int k = 0; k < nList3.getLength(); k++) {
							Node node3 = nList3.item(k);
							if(node3.getNodeType()==Node.ELEMENT_NODE) {								
								System.out.println("pontok :	"+node3.getNodeName()+" : "+node3.getTextContent());
							}
						}
					}
				}
            }
		}
		//összpontok
		System.out.println("Összes pont: "+ sum);
	}
}
