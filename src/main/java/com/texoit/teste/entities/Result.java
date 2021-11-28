package com.texoit.teste.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class Result implements Serializable{
	private static final long serialVersionUID = 1L;

	private String status;
	private String producer;
	@Column(name = "\"interval\"")	
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
	
	public Result() {
	}

	public Result(String status, String producer, Integer interval, Integer previousWin, Integer followingWin) {
		super();
		this.status = status;
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getinterval() {
		return interval;
	}

	public void setinterval(Integer interval) {
		this.interval = interval;
	}
	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}

	public Integer getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}
	
	
}
