package com.kd.memorygame;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HighScoresIO {

	String path;
	boolean isHard;

	public HighScoresIO(boolean isHard) {
		this.isHard = isHard;
		if (isHard) {
			path = "resources/HighScoresHard";
		} else {
			path = "resources/HighScores";
		}

	}

	public HighScores importScores() {

		try {

			HighScores highScores = new HighScores(isHard);
			try {
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				highScores = (HighScores) objectIn.readObject();
				objectIn.close();
			} catch (EOFException e) {
				
			}
			return highScores;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	public boolean exportScores(HighScores highScores) {

		try {

			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(highScores);
			objectOut.close();
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

}
