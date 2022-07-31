package com.kd.memorygame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MemoryGame {

	private List<String> arrayA = new ArrayList<String>();
	private List<String> arrayB = new ArrayList<String>();
	private List<String> hiddenArrayA = new ArrayList<String>();
	private List<String> hiddenArrayB = new ArrayList<String>();
	private List<String> coordinateArray = new ArrayList<String>();
	private int chancesLeft;
	private int totalItems;
	private String hiddenString;
	String coordinates;
	String alphabeticalCoordinate;
	String alphabeticalCoordinateUsed;
	boolean isHard;
	int numericalCoordinate;
	private List<Integer> usedCoordinatesA = new ArrayList<Integer>();
	private List<Integer> usedCoordinatesB = new ArrayList<Integer>();
	private int tempCoordinateA;
	private int tempCoordinateB;

	public MemoryGame() {

	}

	public boolean newMemoryGame(String difficulty) {
		
		
		chooseDifficulty(difficulty);

		CreateWordArray newWordArray = new CreateWordArray(totalItems);

		arrayA = newWordArray.getWordArray();

		RandomizedArray randomize = new RandomizedArray(arrayA);

		arrayB = randomize.randomize(arrayA);

		setHiddenArrays(arrayA);
		hiddenString = hiddenArrayA.get(0);
		setCoordinateArray(arrayA);
		

		masterPlay();

		arrayA.clear();
		arrayB.clear();
		hiddenArrayA.clear();
		hiddenArrayB.clear();
		coordinateArray.clear();
		usedCoordinatesA.clear();
		usedCoordinatesB.clear();
		
		System.out.println("Restart (r) or return to Main Menu (press Enter)");
	
		Scanner scanner = new Scanner(System.in);
//		String restart = scanner.nextLine();
//		if(restart == "r") {
//			System.out.println("Restarting");
//			return true;
//		}else {	
//		return false;
//		}
		
		switch (scanner.nextLine()) {
		case "r":
			System.out.println("Restarting");
			return true;
		default:
			return false;
		}
	
	}

	private void chooseDifficulty(String difficulty) {



		switch (difficulty) {

		case "easy":
			totalItems = 4;
			chancesLeft = 10;
			this.isHard = false;
			break;
		case "hard":
			totalItems = 8;
			chancesLeft = 15;
			this.isHard = true;
			break;
		default:
			System.out.println("Incorrect difficulty");

		}

	}

	private void setHiddenArrays(List<String> array) {
		int wordLength = array.get(0).length();
		String word;
		for (int i = 0; i < array.size(); i++) {
			hiddenArrayA.add("x");
			hiddenArrayB.add("x");
			while (hiddenArrayA.get(i).length() < wordLength) {
				word = hiddenArrayA.get(i) + "x";
				hiddenArrayA.set(i, word);
				hiddenArrayB.set(i, word);
			}
		}

	}

	private void setCoordinateArray(List<String> array) {
		int wordLength = array.get(0).length();
		String word;
		for (int i = 0; i < array.size(); i++) {
			coordinateArray.add(Integer.toString(i + 1));

			while (coordinateArray.get(i).length() < wordLength) {
				word = coordinateArray.get(i) + " ";
				coordinateArray.set(i, word);
				while (coordinateArray.get(i).length() < wordLength) {
					word = " " + coordinateArray.get(i);
					coordinateArray.set(i, word);
					break;
				}
			}
		}

	}

	private void printArrays() {
		System.out.print("   ");
		System.out.println(coordinateArray.toString());

		System.out.print(" A ");
		System.out.println(hiddenArrayA.toString());
		System.out.println("");
		System.out.print(" B ");
		System.out.println(hiddenArrayB.toString());
		System.out.println("");
	}

	private void flipWord(List<String> array, List<String> sourceArray, int coordinate) {
		array.set(coordinate - 1, sourceArray.get(coordinate - 1));
	}

	private boolean compareWords(List<String> arrayA, int coordinateA, List<String> arrayB, int coordinateB) {
		if (arrayA.get(coordinateA - 1).equals(arrayB.get(coordinateB - 1))) {
			return true;
		}
		return false;
	}

	private boolean readCoordinates() {

		try {
			Scanner scanner = new Scanner(System.in);
			coordinates = scanner.nextLine();
			alphabeticalCoordinate = coordinates.substring(0, 1);
			numericalCoordinate = Integer.parseInt(coordinates.substring(1));
			if (!(alphabeticalCoordinate.equals("A")) && !(alphabeticalCoordinate.equals("B"))) {
				System.out.println("Incorrect coordinates, try again");
				return true;
			}
			if (numericalCoordinate > arrayA.size() || numericalCoordinate <= 0) {
				System.out.println("Incorrect coordinates, try again");
				return true;
			}
		} catch (NumberFormatException e) {
			System.out.println("Incorrect coordinates, try again");
			return true;
		}

		return false;

	}

	private boolean playFirst() {

		boolean keepGoing = true;
		System.out.println("Chances left " + chancesLeft);
		printArrays();
		System.out.println("Choose first word (eg A3)");
		while (keepGoing) {
			keepGoing = readCoordinates();
		}
		if (alphabeticalCoordinate.equals("A")) {
			if (usedCoordinatesA.contains(numericalCoordinate)) {
				System.out.println("Word already revealed, try again");
				return true;
			}
			tempCoordinateA = numericalCoordinate;
			flipWord(hiddenArrayA, arrayA, numericalCoordinate);
		}
		if (alphabeticalCoordinate.equals("B")) {
			if (usedCoordinatesB.contains(numericalCoordinate)) {
				System.out.println("Word already revealed, try again");
				return true;
			}
			tempCoordinateB = numericalCoordinate;
			flipWord(hiddenArrayB, arrayB, numericalCoordinate);
		}
		alphabeticalCoordinateUsed = alphabeticalCoordinate;
		return false;
	}

	private boolean playSecond() {
		boolean keepGoing = true;

		printArrays();

		System.out.println("Choose second word");
		keepGoing = true;
		while (keepGoing) {
			keepGoing = readCoordinates();
			if (alphabeticalCoordinate.equals(alphabeticalCoordinateUsed)) {
				System.out.println("Can't choose word from the same line, try again");
				keepGoing = true;
			}
		}
		if (alphabeticalCoordinate.equals("A")) {
			if (usedCoordinatesA.contains(numericalCoordinate)) {
				System.out.println("Word already revealed, try again");
				return true;
			}
			tempCoordinateA = numericalCoordinate;
			flipWord(hiddenArrayA, arrayA, numericalCoordinate);
		}
		if (alphabeticalCoordinate.equals("B")) {
			if (usedCoordinatesB.contains(numericalCoordinate)) {
				System.out.println("Word already revealed, try again");
				return true;
			}
			tempCoordinateB = numericalCoordinate;
			flipWord(hiddenArrayB, arrayB, numericalCoordinate);
		}
		alphabeticalCoordinateUsed = null;

		printArrays();
		if (compareWords(arrayA, tempCoordinateA, arrayB, tempCoordinateB)) {
			// System.out.println("Good job");
			usedCoordinatesA.add(tempCoordinateA);
			usedCoordinatesB.add(tempCoordinateB);
		} else {
			// System.out.println("Try again");
			chancesLeft--;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
			hiddenArrayA.set(tempCoordinateA - 1, hiddenString);
			hiddenArrayB.set(tempCoordinateB - 1, hiddenString);
		}
		return false;
	}

	private void masterPlay() {
		boolean win = false;
		HighScores easy = new HighScores(false);
		HighScores hard = new HighScores(true);
		HighScoresIO hsIOEasy = new HighScoresIO(false);
		HighScoresIO hsIOHard = new HighScoresIO(true);
		
		easy = hsIOEasy.importScores();
		hard = hsIOHard.importScores();
		

		long startTime = System.nanoTime();

		while (chancesLeft > 0) {
			spamConsole();
			boolean keepGoing = true;
			while (keepGoing) {
				keepGoing = playFirst();
			}
			keepGoing = true;
			while (keepGoing) {
				keepGoing = playSecond();
			}

			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;

			if (usedCoordinatesA.size() == arrayA.size()) {
				System.out.println("Congratulations!");
				System.out.println("You have finished the game with " + chancesLeft + " chances left");
				System.out.println("Completion time: " + (timeElapsed/1000000000));
				win = true;
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter player name:");
				Score newScore = new Score(scanner.nextLine(), (timeElapsed/1000000000), chancesLeft, isHard);
				if(isHard) {
					hard.addScore(newScore);
					hsIOHard.exportScores(hard);
					
				}else {
					easy.addScore(newScore);
					hsIOEasy.exportScores(easy);
					
				}
				
				
				System.out.println("High scores (easy):");
				easy.printScores();
				System.out.println("High scores (hard):");
				hard.printScores();
				break;
			}
		}
		if (!win) {
			System.out.println("Better luck next time");
			System.out.println("High scores (easy):");
			easy.printScores();
			System.out.println("High scores (hard):");
			hard.printScores();
		}

	}

	private void spamConsole() {
		for (int i = 0; i <= 50; i++) {
			System.out.println("");
		}

	}

}
