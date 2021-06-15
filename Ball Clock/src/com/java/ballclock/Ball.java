/**
 * 
 */
package com.java.ballclock;

/**
 * @author Toussida F T Minoungou
 *
 */

public class Ball {
	private int number;
	/***
	 * constructor 
	 * @param number
	 *
	 */
	public Ball(int number){
		this.setNumber(number);
	}
	
	/***
	 * 
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	
	/***
	 * 
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
}

