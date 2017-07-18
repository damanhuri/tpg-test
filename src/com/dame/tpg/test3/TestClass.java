package com.dame.tpg.test3;

import java.util.Random;

public class TestClass {
	
	public void testMethod() {
		try {
			// to simulate the random execution time of this method
			// set to random value from within 20 secs
			Thread.sleep(new Random().nextInt(10000) + 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
