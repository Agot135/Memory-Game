package com.kd.memorygame;

public class MemoryGameMain {

	public static void main(String[] args) {
		
		
		MemoryGame game = new MemoryGame();
		
		
		game.newMemoryGame();
		
		System.out.println(game.getArrayA().toString());
		System.out.println(game.getArrayB().toString());

	}

}
