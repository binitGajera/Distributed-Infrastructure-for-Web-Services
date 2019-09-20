package com.aos.controller;

public class Service {
	
	Data data = new Data();
	Load load = new Load();
	Maintain maintain = new Maintain();
	
	public void startConnection() {
		ServerLoad.medium = data.getServer_capacity() / 2;
		ServerLoad.overload = (int) (ServerLoad.medium * 0.6 + ServerLoad.medium); 
		load.start();
		maintain.start();


	}
}
