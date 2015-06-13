package com.newidor.learn.redis.dao;

import java.io.Serializable;


/**
 * 只包含用ID和地址，即Key和Value，使用Redis的get、set命令即可
 * @author Administrator
 */
public class User implements Serializable {

	private static final long serialVersionUID = -1267719235225203410L;

	private String uid;

	private String address;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
