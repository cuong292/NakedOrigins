package com.example.admin.nakedorigins.data.dto;

import com.example.admin.nakedorigins.R;

import java.io.Serializable;

public class Coffee implements Serializable {
	private int bodyPercent;
	private int acidityPercent;
	private int aromaPercent;
	private int bitternessPercent;
	private String name;
	private int id;
	private int resImg;

	public Coffee() {
		this.bodyPercent = 3;//85;
		this.acidityPercent = 2;//= 60;
		this.aromaPercent = 1;//35;
		this.bitternessPercent = 1;//25;
		this.name = "Kati Kati Blend";
		this.resImg = R.drawable.cf_kati_kati;
		this.id = 1;
	}

	public int getBodyPercent() {
		return bodyPercent;
	}

	public void setBodyPercent(int bodyPercent) {
		this.bodyPercent = bodyPercent;
	}

	public int getAcidityPercent() {
		return acidityPercent;
	}

	public void setAcidityPercent(int acidityPercent) {
		this.acidityPercent = acidityPercent;
	}

	public int getAromaPercent() {
		return aromaPercent;
	}

	public void setAromaPercent(int aromaPercent) {
		this.aromaPercent = aromaPercent;
	}

	public int getBitternessPercent() {
		return bitternessPercent;
	}

	public void setBitternessPercent(int bitternessPercent) {
		this.bitternessPercent = bitternessPercent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResImg() {
		return resImg;
	}

	public void setResImg(int resImg) {
		this.resImg = resImg;
	}
}
