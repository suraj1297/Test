package CRUD;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ParseXMLFile {
	
	Element root;
	Document document;
	
	ParseXMLFile() throws SAXException, IOException{
		
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//Build Document
			document = builder.parse(new File(".\\src\\CarProfileData.xml"));
			//Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();
			//Here comes the root node
//			root = document.getDocumentElement();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Document getDocument() {
		
		return document;
	}
}
