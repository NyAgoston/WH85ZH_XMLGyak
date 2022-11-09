package xpathwh85zh;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class xPathWH85ZH {
    public static void main(String[] aStrings) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("studentWH85ZH.xml");

            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            // String expression = "/class/student";
            // String expression = "//student[@id=02]";
            // String expression = "//student";
            // String expression = "/class/student[2]";
            // String expression = "/class/student[last()]";
            // String expression = "/class/student[last()-1]";
            // String expression = "/class/*";
            // String expression = "/class/*[@*>=1]";
            // String expression = "/descendant-or-self::*";
            // String expression = "/class/student[20<age]";
            String expression = "/class/student/firstname | /class/student/lastname";

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                System.out.println("\nAktualis elem: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                    Element element = (Element) node;

                    System.out.println("Hallgato id: " + element.getAttribute("id"));

                    System.out.println(
                            "Keresztnév: " + element.getElementsByTagName("firstname").item(0).getTextContent());

                    System.out.println(
                            "Vezeteknev: " + element.getElementsByTagName("lastname").item(0).getTextContent());

                    System.out.println("Becenev: " + element.getElementsByTagName("nickname").item(0).getTextContent());

                    System.out.println("Kor: " + element.getElementsByTagName("age").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
