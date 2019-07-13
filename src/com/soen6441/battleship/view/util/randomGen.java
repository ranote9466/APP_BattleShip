package com.soen6441.battleship.view.util;

import java.util.Random;

public class randomGen {
	private static Random random;
	
	public static Integer generate(Integer value) {
		random = new Random();
		return random.nextInt(value) + 1;
	}
}

