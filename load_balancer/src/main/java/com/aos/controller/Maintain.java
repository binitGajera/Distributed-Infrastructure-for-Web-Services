package com.aos.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Maintain extends Repo{

	ClientManager client = new ClientManager();
	
	public void start() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				Map tmp = new HashMap();
				tmp.putAll(ServerLoad.lost_server);
				Iterator it = tmp.entrySet().iterator();
				System.out.println(ServerLoad.lost_server + "Lost Server");
				System.out.println(ServerLoad.server_load + "Server Load");
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					int val = (Integer) pair.getValue();
					System.out.println(pair.getKey() + " = " + pair.getValue());
					if (val == 0) {
						String key = pair.getKey().toString();
						//repo.removeServer(key);
						//Map<String, String> server_load = new HashMap<String, String>();
						//server_load = repo.getServer_load();
						System.out.println(server_load+"YOOO");
						server_load.remove(key);
						System.out.println(server_load+"YOOO");
						ServerLoad.server_load.remove(key);
						ServerLoad.lost_server.remove(key);
						client.removeServer(key);
					} else {
						ServerLoad.lost_server.put(pair.getKey().toString(), 0);
					}
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, new Date(), 5000);
	}

}
