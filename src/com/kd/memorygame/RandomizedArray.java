package com.kd.memorygame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizedArray {

	public RandomizedArray(List<String> inArray) {
		// TODO Auto-generated constructor stub
	}

	protected List<String> randomize(List<String> inArray) {

		List<String> randomizedArray = new ArrayList<String>();
		Random rand = new Random();
		int arraySize = inArray.size();

		List<String> inArrayTemp = new ArrayList<String>(inArray);

		for (int i = 0; i < arraySize; i++) {

			int randomIndex = rand.nextInt(inArrayTemp.size());

			randomizedArray.add(inArrayTemp.get(randomIndex));

			inArrayTemp.remove(randomIndex);
		}

		return randomizedArray;

	}

}
