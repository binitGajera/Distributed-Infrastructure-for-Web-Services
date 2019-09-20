package com.aos.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Load {

	Repo repo = new Repo();

	public void start() {
		System.out.println("Load Balancer Started: ");
		TimerTask task = new TimerTask() {
 
			@Override
			public void run() {
				System.out.println(ServerLoad.server_load);			
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, new Date(), 3000);
	}

}
