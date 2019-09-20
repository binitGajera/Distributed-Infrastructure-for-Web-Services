package com.aos.controller;

import com.aos.servers.LoadRequest;
import com.aos.servers.Result;

public class Service {

	ClientManager client = new ClientManager();

	public void startConnection() {
		Load load = new Load();
		Data data = new Data();
		ServerLoad.medium = data.getServer_capacity() / 2;
		ServerLoad.overload = (int) (ServerLoad.medium * 0.6 + ServerLoad.medium); 
		Result result = new Result();
		result = client.registerServer();
		load.start(result.getResult());
	}
}
