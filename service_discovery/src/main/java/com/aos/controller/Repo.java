package com.aos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aos.servers.Request;
import com.aos.servers.Result;
import com.aos.servers.ServerIdentity;

public class Repo {

	public Map<String, String> server_names = new HashMap<String, String>();
	public Map<String, HashSet<String>> server_services = new HashMap<String, HashSet<String>>();
	public ClientManager service = new ClientManager();

	public void registerServer(ServerIdentity server_identity) {
		int i = 0, j = 0, name = 0;
		String s_name = server_identity.getName();
		String s_ip = server_identity.getIp();
		if (!server_names.containsKey(server_identity.getName())) {
			service.addtoLoadBalancer(s_name);
		}
		server_names.put(s_name, s_ip);
		String services[] = server_identity.getServices().split("\\s*,\\s*");
		for (i = 0; i < services.length; i++) {
			if (server_services.containsKey(services[i])) {
				HashSet<String> servers = new HashSet<String>();
				servers = server_services.get(services[i]);
				servers.add(s_name);
			} else {
				HashSet<String> servers = new HashSet<String>();
				servers.add(s_name);
				server_services.put(services[i], servers);
			}
		}
		System.out.println(server_names);
		System.out.println(server_services);
	}

	public Result clientRequest(Request request) {
		String operation = request.getOperation().toLowerCase();
		// System.out.println(request.toString());
		String least_server = getLeastLoadServer(operation);
		String ip = server_names.get(least_server);
		// System.out.println(ip+": ip of least load server ");
		Result answer = service.solveOperation(ip, request);
		return answer;

	}

	public String getLeastLoadServer(String operation) {
		HashSet<String> servers = new HashSet<String>();
		// System.out.println(server_services);
		servers = server_services.get(operation);
		List list = new ArrayList(servers);
		// System.out.println("serverList: "+servers);
		Result result = service.getLeastLoadServer(list);
		return result.getResult();
	}

	public void deleteServer(String request) {
		server_names.remove(request);
		System.out.println(server_names);
		for (Map.Entry<String, HashSet<String>> entry : server_services.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue());
			Set<String> row = entry.getValue();
			row.remove(request);
		}
		System.out.println(server_names);
		System.out.println(server_services);
	}

}
