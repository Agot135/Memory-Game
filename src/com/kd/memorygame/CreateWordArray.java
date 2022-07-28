package com.kd.memorygame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateWordArray {

	private List<String> wordArray = new ArrayList<>();
	private List<String> allWords = new ArrayList<>();

	protected CreateWordArray(int totalItems) {
		readFile();
		
		newWordArray(allWords, totalItems);

	}

	protected List<String> getWordArray() {
		return wordArray;
	}

	private void newWordArray(List<String> list, int totalItems) {

		Random rand = new Random();

		List<String> newList = new ArrayList<>();
		for (int i = 0; i < totalItems; i++) {

			int randomIndex = rand.nextInt(list.size());

			newList.add(list.get(randomIndex));

			list.remove(randomIndex);
		}
		wordArray = normalizeWords(newList);
	}

	private void readFile() {

		FileReader fr = null;
		String linia = "";

		try {
			fr = new FileReader("resources/Words.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Error during file loading");
			System.exit(1);
		}

		BufferedReader bfr = new BufferedReader(fr);

		try {
			while ((linia = bfr.readLine()) != null) {
				allWords.add(linia);
			}
		} catch (IOException e) {
			System.out.println("Error during file reading");
			System.exit(2);
		}

		try {
			fr.close();
		} catch (IOException e) {
			System.out.println("Error during file closing");
			System.exit(3);
		}

	}
	
	private int checkLongestString(List<String> array) {
		int maxLength = array.get(0).length();
		for (int i = 0; i < array.size() - 1; i++) {
			if (maxLength < array.get(i + 1).length()) {
				maxLength = array.get(i + 1).length();
				
			}
		}
		
		return maxLength;
	}
	
	private List<String> normalizeWords(List<String> array) {
		int wordLength = checkLongestString(array);

		for (int i = 0; i < array.size(); i++) {
			String word = array.get(i);
			while (array.get(i).length() < wordLength) {
				word = array.get(i) + " ";
				array.set(i, word);
			}
		}
		return array;

	}

}
