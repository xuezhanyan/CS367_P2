
/////////////////////////////////////////////////////////////////////////////
// Semester: CS367 Spring 2017
// PROJECT: Program 2
// FILE: Config.java, Game.java, GameApp.java, Job.java, JobList.java
// JobListIterator.java, JobSimulator.java, ListADT.java,
// Listnode.java, ScoreboardADT.java, Scoreboard.java,
//
// TEAM: Team 57 import teamName
// Authors: Matthew Schmude, Xuezhan Yan, Kellie Stein, Shuo Sean Li, Da Chen, Andy Kempken
// Author1: Matthew Schmude, schmude@wisc.edu, 9074395576, Lec 002
// Author2: Xuezhan Yan, xyan56@wisc.edu, 9074973794, Lec 002
// Author3: Kellie Stein, kstein5@wisc.edu, 9075112731, Lec 002
// Author4: Shuo Sean Li, sli486@wisc.edu, 9074080509, Lec 002
// Author5: Da Chen, dchen95@wisc.edu, 9070275327, Lec 002
// Author6: Andy Kempken, akempken@wisc.edu, 9072543862, Lec 002
//
// ---------------- OTHER ASSISTANCE CREDITS
// Persons: Identify persons by name, relationship to you, and email.
// Describe in detail the the ideas and help they provided.
//
// Online sources: avoid web searches to solve your problems, but if you do
// search, be sure to include Web URLs and description of
// of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Scanner;

/**
 * This is the main class that starts the program execution and starts the main program loop. It
 * takes two command line arguments, a seed for the random number generator and a positive integer
 * indicating the duration.
 *
 * @author Team 57 import teamName
 */
public class GameApp {
	/**
	 * Scanner instance for reading input from console
	 */
	private static final Scanner STDIN = new Scanner(System.in);

	/**
	 * Game instance for invoking methods to run the game
	 */
	// do not declare this as static field is bc we might have more than one game to play at a time
	private Game thisGame;

	/**
	 * Constructor for instantiating game class
	 * 
	 * @param seed:
	 *            Seed value as processed in command line
	 * @param timeToPlay:
	 *            Total time to play from command line
	 */
	public GameApp(int seed, int timeToPlay) {
		thisGame = new Game(seed, timeToPlay);
	}

	/**
	 * Main function which takes the command line arguments and instantiate the GameApp class. The
	 * main function terminates when the game ends. Use the getIntegerInput function to read inputs
	 * from console
	 *
	 * @param args:
	 *            Command line arguments <seed> <timeToPlay>
	 */
	public static void main(String[] args) {
		int seed, timeToPlay;
		// check whether two command line arguments are integer
		try {
			seed = Integer.parseInt(args[0]);
			timeToPlay = Integer.parseInt(args[1]);
		} catch (Exception ex) {
			System.out.println(
					"Invalid command line arguments, seed  and duration should both be positive integers.");
			return;
		}
		// time to play are positive
		if (timeToPlay <= 0)
			return;
		GameApp thisGameApp = new GameApp(seed, timeToPlay);
		/* Game Begin Here */
		System.out.println("Welcome to the Job Market!");
		thisGameApp.start();
	}

	/**
	 * The method implements how calling methods interact game logic. There is a basic program loop
	 * repeated continuously until the game is over. Game logic is explained by comments line by
	 * line. All places where user input is required error checking
	 */
	private void start() {
		while (!thisGame.isOver()) {
			// 1. Display how much time remains in the game
			System.out.println("You have " + thisGame.getTimeToPlay() + " left in the game!");
			// 2. Use the Game object to create new jobs
			thisGame.createJobs();
			// 3. Use the Game object to display all jobs
			thisGame.displayActiveJobs();
			// 4. Prompt the user for an index of a job to work on. There is a time penalty for
			// picking a job that is not at index 0.
			int index_of_job = getIntegerInput("Select a job to work on: ");
			// 5. Prompt the user for an amount of time to work on this job
			// 6. Get the job stored at the given index and attempt to work on it for the given
			// amount of time.
			/*
			 * Using Math.min() is to handle the special case of if input amount of time the job is
			 * intended to be worked on exceeds the remaining time of the game, the job must be
			 * progressed for at most the amount of time remaining in the game
			 */
			int time_on_job = Math.min(thisGame.getTimeToPlay(),
					getIntegerInput("For how long would you like to work on this job?: "));
			// 7. Update the job for the specified amount of time
			/* if return null, this means this job is done. Implemented in updateJob() */
			Job thisJob = thisGame.updateJob(index_of_job, time_on_job);
			if (thisJob != null) {
				// 8. if not return null means job not done
				int index_of_insert_job = getIntegerInput(
						"At what position would you like to insert the job back into the list?\n");
				thisGame.addJob(index_of_insert_job, thisJob);
			} else {
				// Job completed
				System.out.println("Job completed! Current Score: " + thisGame.getTotalScore());
				System.out.println("Total Score: " + thisGame.getTotalScore());
				thisGame.displayCompletedJobs();
			}
			/* Ending Game Message */
			if (thisGame.isOver()) {
				System.out.println("Game Over!");
				System.out.println("Your final score: " + thisGame.getTotalScore());
			}
		}
	}

	/**
	 * Displays the prompt and returns the integer entered by the user to the standard input stream.
	 *
	 * Does not return until the user enters an integer. Does not check the integer value in any
	 * way.
	 * 
	 * @param prompt
	 *            The user prompt to display before waiting for this integer.
	 */
	public static int getIntegerInput(String prompt) {
		System.out.print(prompt);
		while (!STDIN.hasNextInt()) {
			System.out.print(STDIN.next() + " is not an int.  Please enter an integer.");
		}
		return STDIN.nextInt();
	}
}