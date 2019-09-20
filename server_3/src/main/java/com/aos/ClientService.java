package com.aos;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.aos.controller.Data;
import com.aos.controller.ServerEndpoint;
import com.aos.controller.ServerLoad;
import com.aos.servers.LoadRequest;
import com.aos.servers.Result;
import com.aos.servers.ServerIdentity;

public class ClientService extends WebServiceGatewaySupport {

	Data data = new Data();
	StringBuilder ip;
	String name = data.getName();
	String port = data.getPort();
	String sd = data.getSd();
	String servicesList = data.getServices();
	LoadRequest request = new LoadRequest();

	public Result registerServer() {
		String uri = sd;
		StringBuilder myip = new StringBuilder();
		ServerIdentity request = new ServerIdentity();
		myip.append("http://");
		myip.append(data.getIp());
		myip.append(":");
		myip.append(port);
		myip.append("/ws");
		request.setIp(myip.toString());
		request.setName(name);
		request.setServices(servicesList);
		Result response = (Result) getWebServiceTemplate().marshalSendAndReceive(uri, request);
		return response;
	}

	public void sendLoad(String ip) {
		String uri = ip;
		request.setLoad(ServerLoad.load);
		request.setName(name);
		LoadRequest response = (LoadRequest) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

}
