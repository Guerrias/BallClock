/**
 * 
 */
package com.java.ballclocktest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.ballclock.BallClock;

/**
 * @author Toussida F T Minoungou
 *
 */
class BallClockTest {

	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	/**
	 * Test method for {@link com.java.ballclock.BallClock#main(java.lang.String[])}.
	 */
	@Test
	void testMain() {
		
	    String[] args = null;
		BallClock.main(args);
		int days = BallClock.getCycleCount() * BallClock.getNumberHoursPerCycle()/BallClock.getNumberHoursPerDay();
		String expectedOutPut = BallClock.getBallsNum()+" balls take "+days+" days"+" for a computation time of "+(BallClock.getEndTime() - BallClock.getStartTime())+" ms";
		assertEquals(expectedOutPut, outContent.toString());	   
	}

}
