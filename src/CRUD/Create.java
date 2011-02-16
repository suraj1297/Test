package CRUD;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import profile.Profile;

public class Create {

	Profile carProfile;
	Document doc;
	Element root;
	Element car;
	ArrayList<String[]> serviceRecords;

	public Create(Profile profile) throws SAXException, IOException, TransformerException {

		this.carProfile = profile;
		
		ParseXMLFile xmlData = new ParseXMLFile();
		this.doc = xmlData.getDocument();
		
		BufferedImage img = carProfile.getPhoto();
		
		System.out.println(img.getType());
		
//		createProfile();
	}

	public void createProfile() throws TransformerException, FileNotFoundException {

		root = doc.getDocumentElement();
		car = doc.createElement(carProfile.getLicencePlate());
		root.appendChild(car);

		createEle("brand", carProfile.getBrand(), false);
		createEle("model", carProfile.getModel(), false);
		createEle("color", carProfile.getColor(), false);
		createEle("year", carProfile.getYear(), false);
		createEle("engineNo", carProfile.getEngineNo(), false);
		createEle("seatsNo", carProfile.getSeatsNo(), false);
		createEle("licensePlate", carProfile.getBrand(), false);
		createEle("ownerName", carProfile.getOwnerName(), false);
		createEle("telephoneNo", carProfile.getTelephoneNo(), false);
		createEle("email", carProfile.getEmail(), false);
		createEle("licenseNo", carProfile.getLicenseNo(), false);
		createEle("socialSecurityNumber", carProfile.getSocialSecurityNumber(), false);
		createEle("address", carProfile.getAddress(), false);
		createEle("warrantyYear", carProfile.getWarrantyYear(), false);

		serviceRecords = carProfile.getServiceRecords();
		Element sr = doc.createElement("ServiceRecord");
		car.appendChild(sr);

		for (int i = 0; i < serviceRecords.size(); i++) {
			Element rec = doc.createElement("record");
			sr.appendChild(rec);

			rec.appendChild(createEle("date", serviceRecords.get(i)[0], true));
			rec.appendChild(createEle("mileage", serviceRecords.get(i)[1], true));
			rec.appendChild(createEle("engineCheck", serviceRecords.get(i)[2], true));

		}
		
		BufferedImage img = carProfile.getPhoto();
		
		System.out.println(img.getType());
		
		
//		FileOutputStream output = new FileOutputStream(".\\src\\CarProfileData.xml");
//		writeXml(doc, output);

	}

	public Element createEle(String tag, String Value, Boolean parent) {
		Element ele = doc.createElement(tag);
		ele.setTextContent(Value);
		if (parent) {
			return ele;
		}
		car.appendChild(ele);
		return null;
	}

	private static void writeXml(Document doc, OutputStream output) throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);

		transformer.transform(source, result);

	}
}
