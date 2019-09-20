package com.aos.controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Data {

	String name = "server2";
	String port = "49153";
	String services = "sub,multi,divide";
	String sd = "http://192.168.43.247:49155/ws";
	int server_capacity = 50;

	public int getServer_capacity() {
		return server_capacity;
	}

	public void setServer_capacity(int server_capacity) {
		this.server_capacity = server_capacity;
	}

	public String getIp() {
		StringBuilder ip = new StringBuilder();
		try {
			ip.append(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	@Override
	public String toString() {
		return "Data [name=" + name + ", port=" + port + ", services=" + services + ", sd=" + sd + ", server_capacity="
				+ server_capacity + "]";
	}

}
