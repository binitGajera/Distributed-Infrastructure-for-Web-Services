package com.aos.controller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.aos.ClientService;
import com.aos.servers.Result;

public class ClientManager extends WebServiceGatewaySupport {

	ClientService client = new ClientService();

	public Result registerServer() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.aos.servers");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		Result response = client.registerServer();
		return response;
	}

	public void sendLoad(String ip) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.aos.servers");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.sendLoad(ip);
	}

}
