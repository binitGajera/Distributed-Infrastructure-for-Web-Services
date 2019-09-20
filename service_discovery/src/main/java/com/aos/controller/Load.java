package com.aos.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Load {

	Data data = new Data();
	ClientManager service = new ClientManager();
	String lb_interval = data.getLb_interval();
	
	public void start(String ip) {
		TimerTask task = new TimerTask() {
 
			@Override
			public void run() {
				//service.sendLoad(ip);			
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, new Date(), 3000);
	}

}
