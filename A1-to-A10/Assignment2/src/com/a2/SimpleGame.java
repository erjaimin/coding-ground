package com.a2;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import com.interfaces.Ring;

/**
 * A program to play the dice-rolling game given set of players and eliminating
 * them if they roll 2, 3 or 12. The game continues until only one player is
 * left in the ring and declared the winner.
 * <p>
 * To simplify the implementation, only string (player name) is allowed in the MyRing.
 * <p>
 * 
 * @author Khalid ALZHRANI
 * @version 1.0
 *
 */
public class SimpleGame {

	/**
	 * main method
	 */
	public static void main(String[] args) {
		//welcome message
		System.out.println("\n" + "This program plays a game where each player rolls the dice and is eliminated "
				+ "if they roll a 2, a 3 or a 12. Last player left in the game wins.\n");

		//seek user input for number of players
		System.out.println("How many people are there in the game?");
		Scanner scan = new Scanner(System.in);
		try {
			int noOfPlayers = scan.nextInt();

			//create a ring the populates with no. of players
			Ring<String> ring = new MyRing<>();
			for (int i = 0; i < noOfPlayers; i++) {
				ring.add("Player #" + i);
			}
			
			//get the iterator for taking turns
			MyRingIterator<String> iterator = ring.getIterator();
			Random r = new Random();
			System.out.println("Here we go!");
			
			
			while (!ring.isEmpty()) {
				String player = iterator.next();
				//rolling pair of dice
				int rollNo = r.nextInt(12) + 1;
				System.out.println(player + " rolls a " + rollNo);
				if (rollNo == 2 || rollNo == 3 || rollNo == 12) {
					iterator.eliminate();
					System.out.println(player + " is eliminated.");
					System.out.println("Remaining players: " + ring);
					if (ring.getCurrentSize() == 1) {
						System.out.println("The winner is " + ring.toArray()[0] + "!");
						break;
					}
				}
				//reseting iterator to continue game until the winner is decided
				if(!iterator.hasNext()){
					iterator = new MyRingIterator<>();
				}
			}
		} catch (InputMismatchException ex) {
			System.err.println("Invalid Input, Try again!");
		} finally {
			if (scan != null)
				scan.close();
		}
	}
}