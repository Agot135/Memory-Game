package com.kd.memorygame;

import java.io.Serializable;
import java.util.LinkedList;

public class HighScores implements Serializable{

	private LinkedList<Score> highScores = new LinkedList<>();
	private boolean isHard;
	

	public HighScores(boolean isHard) {
		this.isHard = isHard;
		// TODO Auto-generated constructor stub
	}

//	public List<Score> getHighScores() {
//		return highScores;
//	}

	public Score getScore(int scorePosition) {
		return highScores.get(scorePosition);
	}

	public boolean addScore(Score newScore) {

		try {
			for (int i = 0; i < 10; i++) {
				if (getScore(i).getChancesLeft() <= newScore.getChancesLeft()) {
					if (getScore(i).getTimeElapsed() > newScore.getTimeElapsed()) {
						highScores.add(i, newScore);
						if (highScores.size() > 10) {
							highScores.removeLast();
						}
						return true;
					}
				}
			}
		} catch (Exception e) {
			addScoreFirst(newScore);
			return true;
		}

		return false;
	}

	public void printScores() {

		if(!highScores.isEmpty()) {
			for (int i = 0; i < highScores.size(); i++) {
				
				System.out.println((i+1) + ". " + highScores.get(i).toString());
				
			}
		}else {
			System.out.println("No valid scores in this category");
		}
		
		

	}
	
	public boolean getIsHard() {
		return this.isHard;
	}
	
	
	private void addScoreFirst(Score newScore) {
		highScores.add(newScore);
	}

}
