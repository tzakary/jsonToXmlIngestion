package jsonToXmlIngestionSaalim;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MediaPointsIngestionJSONtoXML {

	public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, TransformerException {
		//Making media points' .json file parsing ready
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\Saalim.ali\\ctaas-automation\\SalesSuiteAutomation\\src\\main\\java\\jsonToXmlIngestionSaalim\\jsonFiles\\mediaPointsData.json");
		Object mediaPointsJavaObject = parser.parse(reader);
		JSONObject mediaPointsJsonObject = (JSONObject) mediaPointsJavaObject;
		
		//Initiating an XML file
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document xmlDoc = documentBuilder.newDocument();
		
		//Inserting an anchoring node to the XML document
		Element anchor = xmlDoc.createElement("anchor");
		xmlDoc.appendChild(anchor);
		
		//Capturing the media points in an array
		JSONArray mediaPointsJsonArray = (JSONArray) mediaPointsJsonObject.get("MediaPoint");
		
		//Iterating each and every media point present in the given JSON object
		for (int i = 0; i < mediaPointsJsonArray.size(); i++) {
			JSONObject mediaPointJsonObject = (JSONObject) mediaPointsJsonArray.get(i);
			
			//Adding media point tag to the XML document
			Element mediaPointTag = xmlDoc.createElement("MediaPoint");
			anchor.appendChild(mediaPointTag);
			
			//This condition checks whether a given media point contains the apply tag or the remove tag
			if (mediaPointJsonObject.containsKey("Apply")) {
				JSONObject applyJsonObject = (JSONObject) mediaPointJsonObject.get("Apply");
				
				//Adding apply tag to the XML document
				Element applyTag = xmlDoc.createElement("Apply");
				mediaPointTag.appendChild(applyTag);
				
				JSONObject policyJsonObject = (JSONObject) applyJsonObject.get("Policy");
				
				//Adding policy tag to the XML document
				Element policyTag = xmlDoc.createElement("Policy");
				applyTag.appendChild(policyTag);
				
				JSONObject viewingPolicyJsonObject = (JSONObject) policyJsonObject.get("ViewingPolicy");
				
				//Adding viewing policy tag to the XML document
				Element viewingPolicyTag = xmlDoc.createElement("ViewingPolicy");
				policyTag.appendChild(viewingPolicyTag);
				
				JSONObject audienceJsonObject = (JSONObject) viewingPolicyJsonObject.get("Audience");
				
				//Adding audience tag to the XML document
				Element audienceTag = xmlDoc.createElement("Audience");
				viewingPolicyTag.appendChild(audienceTag);
				
				JSONArray zipJsonArray = (JSONArray) audienceJsonObject.get("Zip");
				for (int j = 0; j < zipJsonArray.size(); j++) {
					String zip = (String) zipJsonArray.get(j);
					//Adding zip tags to the XML document
					Element zipTag = xmlDoc.createElement("Zip");
					audienceTag.appendChild(zipTag);
					
					//Setting value of the zip tag using the data read from the JSON object
					zipTag.setTextContent(zip);
				}
				
				String content = (String) viewingPolicyJsonObject.get("Content");
				//Adding content tag to the XML document
				Element contentTag = xmlDoc.createElement("Content");
				viewingPolicyTag.appendChild(contentTag);
				
				//Setting value of the content tag using the data read from the JSON object
				contentTag.setTextContent(content);
				
				String matchSignal = (String) mediaPointJsonObject.get("MatchSignal");
				//Adding match signal tag to the XML document
				Element matchSignalTag = xmlDoc.createElement("MatchSignal");
				mediaPointTag.appendChild(matchSignalTag);
				
				//Setting value of the content tag using the data read from the JSON object
				matchSignalTag.setTextContent(matchSignal);
			}
			
			//If the media point has a remove tag following it, then this else code block will execute
			else {
				JSONObject removeJsonObject = (JSONObject) mediaPointJsonObject.get("Remove");
				
				//Adding apply tag to the XML document
				Element removeTag = xmlDoc.createElement("Remove");
				mediaPointTag.appendChild(removeTag);
				
				JSONObject policyJsonObject = (JSONObject) removeJsonObject.get("Policy");
				
				//Adding policy tag to the XML document
				Element policyTag = xmlDoc.createElement("Policy");
				removeTag.appendChild(policyTag);
				
				JSONObject viewingPolicyJsonObject = (JSONObject) policyJsonObject.get("ViewingPolicy");
				
				//Adding viewing policy tag to the XML document
				Element viewingPolicyTag = xmlDoc.createElement("ViewingPolicy");
				policyTag.appendChild(viewingPolicyTag);
				
				JSONObject audienceJsonObject = (JSONObject) viewingPolicyJsonObject.get("Audience");
				
				//Adding audience tag to the XML document
				Element audienceTag = xmlDoc.createElement("Audience");
				viewingPolicyTag.appendChild(audienceTag);
				
				JSONArray zipJsonArray = (JSONArray) audienceJsonObject.get("Zip");
				for (int j = 0; j < zipJsonArray.size(); j++) {
					String zip = (String) zipJsonArray.get(j);
					//Adding zip tag to the XML document
					Element zipTag = xmlDoc.createElement("Zip");
					audienceTag.appendChild(zipTag);
					
					//Setting value of the zip tag using the data read from the JSON object
					zipTag.setTextContent(zip);
				}
				
				String content = (String) viewingPolicyJsonObject.get("Content");
				//Adding content tag to the XML document
				Element contentTag = xmlDoc.createElement("Content");
				viewingPolicyTag.appendChild(contentTag);
				
				//Setting value of the content tag using the data read from the JSON object
				contentTag.setTextContent(content);
				
				String matchSignal = (String) mediaPointJsonObject.get("MatchSignal");
				//Adding match signal tag to the XML document
				Element matchSignalTag = xmlDoc.createElement("MatchSignal");
				mediaPointTag.appendChild(matchSignalTag);
				
				//Setting value of the content tag using the data read from the JSON object
				matchSignalTag.setTextContent(matchSignal);
			}
			
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source source = new DOMSource(xmlDoc);
		Result resultDestination = new StreamResult(System.out);
		transformer.transform(source, resultDestination);
	}

}
