package com.aos.controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.aos.servers.Request;
import com.aos.servers.Result;
import com.aos.servers.ServerIdentity;

@Endpoint
public class ServerEndpoint {
	String ip;
	Repo repo = new Repo();

	void serverendpoint() {
		try {
			ip = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "ServerIdentity")
	@ResponsePayload
	public Result processIdentity(@RequestPayload ServerIdentity request) {
		Result response = new Result();
		repo.registerServer(request);
		Data data = new Data();		
		response.setResult(data.getLb());
		return response;
	}
	
	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Request")
	@ResponsePayload
	public Result clientRequest(@RequestPayload Request request) {
		Result response = new Result();
		Instant start = Instant.now();
		response.setResult(repo.clientRequest(request).getResult());
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println(request.getOperation()+" processed in : "+ timeElapsed.toMillis()/1000+"seconds");		
		return response;
	}
	
	@PayloadRoot(namespace = "http://aos.com/servers", localPart = "Result")
	@ResponsePayload
	public Result clientRequest(@RequestPayload Result request) {
		System.out.println("REached");
		Result result = new Result();
		repo.deleteServer(request.getResult());	
		return result;		
	}

}
