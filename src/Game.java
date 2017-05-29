
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

import java.util.Iterator;

/**
 * The Game class is responsible for maintaining the active list of jobs and utilizes the
 * JobSimulator class to create new jobs to be added to the end of the job listing.
 *
 * @author Team 57 import teamName
 */
public class Game {

	/**
	 * A list of all jobs currently in the queue.
	 */
	private ListADT<Job> list;
	/**
	 * Whenever a Job is completed it is added to the scoreboard
	 */
	private ScoreboardADT scoreBoard;
	/**
	 * A integer for storing how much time left playing the game
	 */
	private int timeToPlay;
	/**
	 * A instance of class JobSimulator to create new jobs
	 */
	private JobSimulator jobSimulator;

	/**
	 * Constructor. Initializes all variables.
	 * 
	 * @param seed
	 *            seed used to seed the random number generator in the Jobsimulator class.
	 * @param timeToPlay
	 *            duration used to determine the length of the game.
	 */
	public Game(int seed, int timeToPlay) {
		this.jobSimulator = new JobSimulator(seed);
		this.timeToPlay = timeToPlay;
		this.list = new JobList();
		this.scoreBoard = new Scoreboard();
	}

	/**
	 * Returns the amount of time currently left in the game.
	 * 
	 * @returns the amount of time left in the game.
	 */
	public int getTimeToPlay() {
		return timeToPlay;
	}

	/**
	 * Sets the amount of time that the game is to be executed for. Can be used to update the amount
	 * of time remaining.
	 * 
	 * @param timeToPlay
	 *            the remaining duration of the game
	 */
	public void setTimeToPlay(int timeToPlay) {
		this.timeToPlay = timeToPlay;
	}

	/**
	 * States whether or not the game is over yet.
	 * 
	 * @returns true if the amount of time remaining in the game is less than or equal to 0, else
	 *          returns false
	 */
	public boolean isOver() {
		return timeToPlay <= 0;
	}

	/**
	 * This method simply invokes the simulateJobs method in the JobSimulator object.
	 */
	public void createJobs() {
		jobSimulator.simulateJobs(list, timeToPlay);
	}

	/**
	 * @returns the length of the Joblist.
	 */
	public int getNumberOfJobs() {
		return list.size();
	}

	/**
	 * Adds a job to a given position in the joblist. Also requires to calculate the time Penalty
	 * involved in adding a job back into the list and update the timeToPlay accordingly
	 * 
	 * @param pos
	 *            The position that the given job is to be added to in the list.
	 * @param item
	 *            The job to be inserted in the list.
	 */
	public void addJob(int pos, Job item) {
		if (pos < 0 || pos > list.size() - 1) {
			// time penalty for picking a job that is not valid
			timeToPlay -= list.size();
			list.add(item);
		} else if (pos != 0) {
			// time penalty for picking a job that is not at index 0
			timeToPlay -= pos;
			list.add(pos, item);
		} else {
			// no time penalty for picking a job that is at index 0
			if (list.size() == 0)
				// if there is no items in list we should call add(item) instead of add (index,it)
				list.add(item);
			else
				list.add(0, item);
		}
	}

	/**
	 * Adds a job to the joblist.
	 * 
	 * @param item
	 *            The job to be inserted in the list.
	 */
	public void addJob(Job item) {
		list.add(item);
	}

	/**
	 * Given a valid index and duration, executes the given job for the given duration.
	 *
	 * This function should remove the job from the list and return it after applying the duration.
	 *
	 * This function should set duration equal to the amount of time remaining if duration exceeds
	 * it prior to executing the job. After executing the job for a given amount of time, check if
	 * it is completed or not. If it is, then it must be inserted into the scoreBoard. This method
	 * should also calculate the time penalty involved in executing the job and update the
	 * timeToPlay value accordingly
	 * 
	 * @param index
	 *            The job to be inserted in the list.
	 * @param duration
	 *            The amount of time the given job is to be worked on for.
	 * @return the Job object being worked on if it is not complete; otherwise return null
	 */
	public Job updateJob(int index, int duration) {
		// consuming time on this job
		timeToPlay -= duration;
		if (index != 0) // time penalty for picking a job that is not at index 0
			timeToPlay -= index; // Deduct time penalty from the total game time remaining
		Job thisJob = list.remove(index);
		// comparing time needed for remaining job and duration time
		if (thisJob.getTimeUnits() - thisJob.getSteps() < duration) {
			// this means duration is larger than time need to complete this job
			// add time not be used for doing this job
			timeToPlay += duration - (thisJob.getTimeUnits() - thisJob.getSteps());
			thisJob.setSteps(thisJob.getTimeUnits()); // set this job to completed
		} else
			thisJob.setSteps(thisJob.getSteps() + duration);
		// check whether this job is complete
		if (thisJob.isCompleted()) {
			scoreBoard.updateScoreBoard(thisJob);
			return null;
		} else
			return thisJob;
	}

	/**
	 * This method produces the output for the initial Job Listing, IE: "Job Listing At position:
	 * job.toString() At position: job.toString() ..."
	 *
	 */
	public void displayActiveJobs() {
		System.out.println("Job Listing");
		// TODO
		// for (int i = 0; i < list.size(); i++)
		// System.out.println("At position: " + i + " " + list.get(i).toString());
		int i = 0;
		Iterator<Job> iterator = list.iterator();
		while (iterator.hasNext())
			System.out.println("At position: " + i++ + " " + iterator.next().toString());
		System.out.println("");
	}

	/**
	 * This function simply invokes the displayScoreBoard method in the ScoreBoard class.
	 */
	public void displayCompletedJobs() {
		scoreBoard.displayScoreBoard();
	}

	/**
	 * This function simply invokes the getTotalScore method of the ScoreBoard class.
	 * 
	 * @return the value calculated by getTotalScore
	 */
	public int getTotalScore() {
		return scoreBoard.getTotalScore();
	}
}