package com.kd.memorygame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PrintArt {
	
	private String path = "resources/Art.txt";
	ArrayList<String> art = new ArrayList<String>();

	public PrintArt() {
		readFile();
		
	}
	
	public void printArt() {
		for(int i = 0; i < art.size(); i++) {
			System.out.println(art.get(i));
		}
	}
	
	private void readFile() {

		FileReader fr = null;
		String line = "";

		try {
			fr = new FileReader(path);
		} catch (FileNotFoundException e) {
			System.out.println("Error during file loading");
			System.exit(1);
		}

		BufferedReader bfr = new BufferedReader(fr);

		try {
			while ((line = bfr.readLine()) != null) {
				art.add(line);
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
	
	
}
