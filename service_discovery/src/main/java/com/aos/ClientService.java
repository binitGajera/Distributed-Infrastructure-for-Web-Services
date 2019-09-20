package com.aos;

import java.util.List;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.aos.controller.Data;
import com.aos.servers.LoadRequest;
import com.aos.servers.Request;
import com.aos.servers.Result;
import com.aos.servers.Servers;

public class ClientService extends WebServiceGatewaySupport {
	
	Data data = new Data();
	StringBuilder ip;
	String name;
	String port;
	String sd;
	String servicesList;
	
	public void addtoLoadBalancer(String server) {
		Result request = new Result();
		request.setResult(server);
		String uri = data.getLb();
		LoadRequest response = (LoadRequest) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}
	
	public Result getLeastLoadServer(List servers) {
		Servers request = new Servers();
		request.setServerName(servers);
		String uri = data.getLb();
		Result response = (Result) getWebServiceTemplate().marshalSendAndReceive(uri, request);
		return response;
	}

	public Result solveOperation(String ip,Request request) {
		String uri = ip;
		Result response = (Result) getWebServiceTemplate().marshalSendAndReceive(uri, request);
		return response;
	}

}
