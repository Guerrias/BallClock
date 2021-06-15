/**
 * 
 */
package com.java.ballclock;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.InputMismatchException;

/**
 * @author Toussida F T Minoungou
 *
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
 
public class BallClock {
	private static final int numberHoursPerCycle = 12;
	private static final int numberHoursPerDay = 24;
	private static int cycleCount=0;
	private static int ballsNum = 0;
	private static long endTime = 0L;
	private static long startTime =0L;
	
	public static long getEndTime() {
		return endTime;
	}

	public static long getStartTime() {
		return startTime;
	}

	public static int getBallsNum() {
		return ballsNum;
	}

	public static int getCycleCount() {
		return cycleCount;
	}

	public static int getNumberHoursPerCycle() {
		return numberHoursPerCycle;
	}


	public static int getNumberHoursPerDay() {
		return numberHoursPerDay;
	}

	public static void main(String[] args) {
		
		startTime = System.currentTimeMillis();
		LinkedList<Ball> queue=new LinkedList<Ball>();
		
		Stack<Ball> hourStack=new Stack<>();
		Stack<Ball> fiveminuteStack=new Stack<Ball>();
		Stack<Ball> minuteStack=new Stack<Ball>();
		
		File  file = null;
		String path = Paths.get("").toAbsolutePath().toString()+"/src/input";

		try {
			file = new File(path);
			Scanner scan = new Scanner(file);
			do {
				ballsNum = scan.nextInt();
				//scan.nextLine();
			}while((ballsNum < 27) || (ballsNum > 127));
			
			scan.close();
			
			//int cycleCount=0;
			Ball temp=null;

			// initializing the queue with all the balls
			for(int i=1;i<=ballsNum;i++){ 
				queue.add(new Ball(i));
			}
			
			
			do{
				while(hourStack.size()<=11){ // hours' stack loop
					
					while(fiveminuteStack.size()<=11){ // five-minutes' stack loop
						
						while(minuteStack.size()<=4){	// minutes' stack loop
							
							 temp=queue.remove(); // removes and returns the first element of the queue
							 
							 if(minuteStack.size()==4){ //minute stack length is equal to 4, return to the five-minute loop	
								break;
							 }else {
								minuteStack.push(temp); //Less than 4 balls into the stack
							 }
						}	
						minuteStackToQueue(queue, minuteStack); //Put the minute stack balls back to the queue
						if(fiveminuteStack.size()==11){ //five-minute stack length is equal to 11, return to the five-minute loop
							break;
						}else {
							fiveminuteStack.push(temp); //Less than 11 balls into the five-minute stack
						}
					}
					
					fiveminuteStackToQueue(queue, fiveminuteStack); //Put the minute stack balls back to the queue
				
					if(hourStack.size()==11){ //hour stack length is equal to 11, return to the five-minute loop
						//System.out.println("Hour break");
						break;	
					}else 	{
						hourStack.push(temp); //Less than 11 balls into the hour stack
					}
				}
				
				hourStackToQueue(queue, hourStack); //Put the hour stack balls back to the queue
				queue.add(temp);
				cycleCount++;
				
			}while(!isEqualToOriginal(queue, ballsNum));
			
			endTime = System.currentTimeMillis();
			System.out.print( ballsNum +" balls take "+(cycleCount*numberHoursPerCycle/numberHoursPerDay)+" days"+" for a computation time of "+(endTime - startTime)+" ms");
			
			
		} catch (FileNotFoundException e) {

			System.out.println("File Not Found");
			//e.printStackTrace();
			
		}catch(InputMismatchException e) {
			System.out.println("Input Mismatch Exception: the input should be of the type int");
			//e.printStackTrace();
		}

			
	}
	
	
	public static void minuteStackToQueue(LinkedList<Ball> queue,Stack<Ball> minuteStack){
		for(int i=0;i<4;i++){
			queue.add(minuteStack.pop());
		}
	}
	
	public static void fiveminuteStackToQueue(LinkedList<Ball> queue,Stack<Ball> fiveminuteStack){
		for(int i=0;i<11;i++){
			queue.add(fiveminuteStack.pop());
		}
	}
	
	public static  void hourStackToQueue(LinkedList<Ball> queue,Stack<Ball> hourStack){
		for(int i=0;i<11;i++){
			queue.add(hourStack.pop());
		}
	}
	
	public static boolean isEqualToOriginal(LinkedList<Ball> queue, int ballsNum){
		for(int i=0;i<ballsNum;i++){
			if(queue.get(i).getNumber() != (i+1)){
				return false;
			}
		}
		return true;
	}
}

