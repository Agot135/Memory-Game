package com.kd.memorygame;

import java.util.Scanner;

public class MainMenu {

	MemoryGame memoryGame = new MemoryGame();
	String difficulty = "easy";

	public MainMenu() {
		
	PrintArt art = new PrintArt();
	art.printArt();

	System.out.println("");
	System.out.println("Welcome to Memory Game!");

	}

	public void startMainMenu() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("");
		System.out.println("Choose action:");
		System.out.println("");
		System.out.println("New game (ng)");
		System.out.println("");
		System.out.println("Difficulty settings (ds) Current: " + difficulty);
		System.out.println("");
		System.out.println("High Scores (hs)");
		System.out.println("");
		System.out.println("Quit (q)");

		switch (scanner.nextLine()) {
		case "ng":
			boolean doContinue;
			do {
				doContinue = memoryGame.newMemoryGame(difficulty);
			} while (doContinue);
			if(!doContinue) {
				startMainMenu();
			}
			
			break;

		case "ds":

			System.out.println("Choose difficulty: easy/hard");

			switch (scanner.nextLine()) {

			case "easy":
				difficulty = "easy";
				break;
			case "hard":
				difficulty = "hard";
				break;
			default:
				System.out.println("Incorrect difficulty");

			}

			startMainMenu();
			break;
		case "hs":
			highScores();
			startMainMenu();
			break;
		case "q":
			break;
		default:
			System.out.println("Incorrect command");
			startMainMenu();
			break;
		}

	}

	public void highScores() {
		
		
		
		HighScores easy = new HighScores(false);
		HighScores hard = new HighScores(true);
		HighScoresIO hsIOEasy = new HighScoresIO(false);
		HighScoresIO hsIOHard = new HighScoresIO(true);
		easy = hsIOEasy.importScores();
		hard = hsIOHard.importScores();
		System.out.println("High scores (easy):");
		easy.printScores();
		System.out.println("High scores (hard):");
		hard.printScores();

	}
	
	

}
