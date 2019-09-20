package com.aos;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.aos.controller.Data;
import com.aos.servers.Result;

public class ClientService extends WebServiceGatewaySupport {
	
	Data data = new Data();
	
	public void removeServer(String server) {
		Result request = new Result();
		request.setResult(server);
		String uri = data.getSd();
		Result response = (Result) getWebServiceTemplate().marshalSendAndReceive(uri, request);
	}

}
