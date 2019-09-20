package com.aos.controller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.aos.ClientService;

public class ClientManager extends WebServiceGatewaySupport {

	ClientService client = new ClientService();

	public void removeServer(String request) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.aos.servers");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.removeServer(request);
	}

}
