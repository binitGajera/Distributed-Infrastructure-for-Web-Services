package com.aos.controller;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;

import com.aos.servers.Servers;


public class Data {

	String name = "server1";
	String port = "65438";
	String services = "add,multi";
	String lb = "http://192.168.43.247:49159/ws";
	String lb_interval = "5000";
	
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

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getLb_interval() {
		return lb_interval;
	}

	public void setLb_interval(String lb_interval) {
		this.lb_interval = lb_interval;
	}


	@Override
	public String toString() {
		return "Data [name=" + name + ", port=" + port + ", services=" + services + ", lb=" + lb + ", lb_interval="
				+ lb_interval + "]";
	}



}
