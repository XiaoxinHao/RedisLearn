package com.newidor.learn.redis.dao;

import java.io.Serializable;


/**
 * ���������������Ҫʹ��Redis��hashs���и��¡���ȡ
 * @author Administrator
 */
public class PuzzleUser implements Serializable {
	
	private static final long serialVersionUID = -1267719235225203410L;

	private String uid;

	private String address;
	
	private String mobile;
	
	private String postCode;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	

}
