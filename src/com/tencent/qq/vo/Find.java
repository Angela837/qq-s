package com.tencent.qq.vo;

import java.io.Serializable;

public class Find implements Serializable{
	private int type;//��������
	private String faccount;//�����˺�
	public static final int ONE=1;//��ȷ
	public static final int ALL=2;//����
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFaccount() {
		return faccount;
	}
	public void setFaccount(String faccount) {
		this.faccount = faccount;
	}
	
	

}
