package com.aos.controller;

public class Server {
	private String ip;
	private String name;
	private String services;

	public Server() {
		super();
	}

	public Server(String ip, String name, String services) {
		super();
		this.ip = ip;
		this.name = name;
		this.services = services;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "Server [ip=" + ip + ", name=" + name + ", services=" + services + "]";
	}

	// Getters and Setters omitted

}