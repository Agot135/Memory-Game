package com.kd.memorygame;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Score implements Serializable{

	private String name;
	private String date;
	private long timeElapsed;
	private int chancesLeft;
	private boolean isHard;
	
	
	public Score(String name, long timeElapsed, int chancesLeft, boolean isHard) {
		Date dateRaw = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");		
		this.name = name;
		this.date = formatter.format(dateRaw);
		this.timeElapsed = timeElapsed;
		this.chancesLeft = chancesLeft;
		this.isHard = isHard;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}

	public int getChancesLeft() {
		return chancesLeft;
	}

	public boolean isHard() {
		return isHard;
	}
	
	@Override
	public String toString() {
		return ("Player: " + name + " | Date: " + date + " | Chances left: " + chancesLeft + " | Completion time: " + timeElapsed);
	}
	
}
