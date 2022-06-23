package main;


/**
 * class to generate a random number
 * @author angusburrowes
 *
 */
public class RandomNumber {
	/**
	 * creates a random number within a range 
	 * @param max  int max number
	 * @param min  int min number
	 * @return int a number between the two bounds
	 */

	public int randomNumberInRange(int max, int min) {
		int number = (int)Math.floor(Math.random() * (max - min + 1) + min);
		return (int) Math.abs(number); 
		
	}
	
	
}
