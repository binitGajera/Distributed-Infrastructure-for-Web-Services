package com.aos.controller;

import java.util.List;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.aos.ClientService;
import com.aos.servers.Request;
import com.aos.servers.Result;

public class ClientManager extends WebServiceGatewaySupport {

	ClientService client = new ClientService();
	CreateRequest create_request = new CreateRequest();

	public void addtoLoadBalancer(String request) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.aos.servers");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.addtoLoadBalancer(request);
	}
	
	public Result getLeastLoadServer(List list) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.aos.servers");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		Result response = client.getLeastLoadServer(list);
		return response;
	} 
	
	public Result solveOperation(String ip,Request request) {
		return create_request.sendRequest(ip,request);
	}

}
