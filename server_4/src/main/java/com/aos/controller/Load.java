package com.aos.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Load {

	ClientManager service = new ClientManager();
	int load = 0;

	public void start(String ip) {
		System.out.println("Pinging to Load Balancer: ");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				load = ServerLoad.load;
				System.out.println("Server current load: "+load);
				service.sendLoad(ip);			
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, new Date(), 3000);
	}

}
