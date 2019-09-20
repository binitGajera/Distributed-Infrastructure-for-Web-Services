package com.aos.controller;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aos.servers.Request;
import com.aos.servers.Result;

public class CreateRequest {
	
	private static SOAPMessage createSoapRequest(Request request) throws Exception{
		 MessageFactory messageFactory = MessageFactory.newInstance();
		 SOAPMessage soapMessage = messageFactory.createMessage();
		 SOAPPart soapPart = soapMessage.getSOAPPart();
   	         SOAPEnvelope soapEnvelope = soapPart.getEnvelope(); 
   	        // soapEnvelope.addNamespaceDeclaration("end", "http://endpoint.concretepage.com/");
		 SOAPBody soapBody = soapEnvelope.getBody();
		 SOAPElement soapElement = soapBody.addChildElement(request.getOperation(),"","http://aos.com/servers");
		 SOAPElement num1 = soapElement.addChildElement("num1");
		 SOAPElement num2 = soapElement.addChildElement("num2");
		 num1.addTextNode(String.valueOf(request.getNum1()));
		 num2.addTextNode(String.valueOf(request.getNum2()));
		 soapMessage.saveChanges();
//		 System.out.println("----------SOAP Request------------");
		// soapMessage.writeTo(System.out);
		 return soapMessage;
	 }
	 private static String createSoapResponse(SOAPMessage soapResponse) throws Exception  {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		SOAPBody soapbody = soapResponse.getSOAPBody();
		java.util.Iterator iterator = soapbody.getChildElements();
		SOAPBodyElement bodyElement = (SOAPBodyElement)iterator.next();
		Node node = bodyElement.getFirstChild();
//		System.out.println(node.getNodeName()); 
		String ans = node.getTextContent();
		return ans;
		//System.out.println();
//		soapResponse.getSOAPBody();
//		System.out.println("\n----------SOAP Response-----------");
//		StreamResult result = new StreamResult(System.out);
//		transformer.transform(sourceContent, result);
	 }
	 public static Result sendRequest(String ip, Request request){
		 Result result = new Result();
	        try{
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = ip;
			SOAPMessage soapRequest = createSoapRequest(request);
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			String ans = createSoapResponse(soapResponse);
			result.setResult(ans);
			soapConnection.close();
		}catch (Exception e) {
		     e.printStackTrace();
		}
	        
			return result;
	 }

}
