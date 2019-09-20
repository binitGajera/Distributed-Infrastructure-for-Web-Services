package com.aos.controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.aos.servers.LoadRequest;
import com.aos.servers.Result;
import com.aos.servers.Servers;

@Endpoint
public class ServerEndpoint {
	String ip;
	int load_counter = 0;
	Repo repo = new Repo();

	void serverendpoint() {
		try {
			ip = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "LoadRequest")
	@ResponsePayload
	public void processIdentity(@RequestPayload LoadRequest request) {
		repo.updateLoad(request);
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Result")
	@ResponsePayload
	public void processIdentity(@RequestPayload Result request) {
		repo.addServer(request);
	}
	
	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Servers")
	@ResponsePayload
	public Result processIdentity(@RequestPayload Servers request) {
//		System.out.println("Choose among: "+ request.getServerName());
		String answer = repo.chooseServer(request);
		Result response = new Result();
		response.setResult(answer);
//		System.out.println("Least Loaded: "+ answer);  
		return response;
	}
 
}
