package com.aos.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.aos.servers.LoadRequest;
import com.aos.servers.Result;
import com.aos.servers.Servers;
import com.aos.controller.ServerLoad;

public class Repo {

	public static Map<String, String> server_load = new HashMap<String, String>();

	public void updateLoad(LoadRequest request) {
		if (server_load.containsKey(request.getName())) {
			String server_status = getServerStatus(request.getLoad());
			server_load.put(request.getName(), server_status);
			ServerLoad.server_load.put(request.getName(), server_status);
			ServerLoad.lost_server.put(request.getName(), 1);
			//System.out.println(server_load+"server_load_real");
		}
	}

	public void addServer(Result request) {
		server_load.put(request.getResult(), "U");
		ServerLoad.server_load.put(request.getResult(), "U");
		System.out.println("New Server added: " + request.getResult());
	}
	
	public void removeServer(String request) {
		//System.out.println(request+"to be removed");
		//System.out.println(server_load+"reall");
		server_load.remove(request);
		//server_load.remove(request);
		ServerLoad.server_load.remove(request);
		ServerLoad.lost_server.remove(request);
//		System.out.println(server_load+"real ppachi");
//		System.out.println(ServerLoad.server_load);
//		System.out.println(ServerLoad.lost_server);
		
	}
	
	public String chooseServer(Servers request) {
		String server = null;
		int length = request.getServerName().size();
		for(int i = 0; i < length; i++) {
			String server_name = request.getServerName().get(i);
			String status = server_load.get(server_name);
			if(status.equals("U")) {
				server = server_name;
				return server;
			}
		}
		for(int i = 0; i < length; i++) {
			String server_name = request.getServerName().get(i);
			String status = server_load.get(server_name);
			if(status.equals("M")) {
				server = server_name;
				return server;
			}
		}
		for(int i = 0; i < length; i++) {
			String server_name = request.getServerName().get(i);
			String status = server_load.get(server_name);
			if(status.equals("O")) {
				server = server_name;
				return server;
			}
		}
		return "no_server";
	}


	public String getServerStatus(int load) {
		String server_status = null;
		if (load < ServerLoad.medium) {
			server_status = "U";
		}
		if (load >= ServerLoad.medium && load < ServerLoad.overload) {
			server_status = "M";
		}
		if (load >= ServerLoad.overload) {
			server_status = "O";
		}
		return server_status;
	}

	public Map<String, String> getServer_load() {
		return server_load;
	}

	public void setServer_load(Map<String, String> server_load) {
		this.server_load = server_load;
	}
	
	

}
