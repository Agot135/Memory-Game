package com.kd.memorygame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoryGame {

	private List<String> arrayA = new ArrayList<String>();
	private List<String> arrayB = new ArrayList<String>();
	private List<String> hiddenArrayA = new ArrayList<String>();
	private List<String> hiddenArrayB = new ArrayList<String>();
	private int chancesLeft = 10;
	private int totalItems = 4;

	public MemoryGame() {
		// TODO Auto-generated constructor stub
	}

	public void newMemoryGame() {

		chooseDifficulty();

		CreateWordArray newWordArray = new CreateWordArray(totalItems);

		arrayA = newWordArray.getWordArray();

		RandomizedArray randomize = new RandomizedArray(arrayA);

		arrayB = randomize.randomize(arrayA);

	}

	public List<String> getArrayA() {

		return arrayA;
	}

	public List<String> getArrayB() {
		return arrayB;
	}

	public void chooseDifficulty() {

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Choose difficulty: easy/hard");
			System.out.println("Default: easy");
			String difficulty = scanner.nextLine();

			switch (difficulty) {

			case "easy":
				totalItems = 4;
				chancesLeft = 10;
				break;
			case "hard":
				totalItems = 8;
				chancesLeft = 15;
				break;
			default:
				System.out.println("Incorrect difficulty");

			}
		}

	}

}
