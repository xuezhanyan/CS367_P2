
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
 * This class implements the ScoreboardADT and contains a single ListADT<Job> that contains only
 * jobs that have been fully completed.
 *
 * @author Team 57 import teamName
 */
public class Scoreboard implements ScoreboardADT {
	/**
	 * A single ListADT<Job> that contains only jobs that have been fully completed.
	 */
	ListADT<Job> completedJobs;

	/**
	 * Constructor for instantiating completedJobs to a new JobList class instance
	 * 
	 */
	public Scoreboard() {
		completedJobs = new JobList();
	}

	/**
	 * Calculates the total combined number of points for every job in the scoreboard.
	 * 
	 * @return The summation of all the points for every job currently stored in the scoreboard.
	 */
	@Override
	public int getTotalScore() {
		int totalSocre = 0;
		// TODO
		// for (int i = 0; i < completedJobs.size(); i++)
		// totalSocre += completedJobs.get(i).getPoints();
		Iterator<Job> iterator = completedJobs.iterator();
		while (iterator.hasNext())
			totalSocre += iterator.next().getPoints();
		return totalSocre;
	}

	/**
	 * Inserts the given job at the end of the scoreboard.
	 * 
	 * @param job
	 *            The job that has been completed and is to be inserted into the list.
	 * @throws IllegalArgumentException
	 *             if item is null
	 */
	@Override
	public void updateScoreBoard(Job job) {
		if (job == null)
			throw new IllegalArgumentException();
		completedJobs.add(job);
	}

	/**
	 * Prints out a summary of all jobs currently stored in the scoreboard. The formatting must
	 * match the example exactly.
	 */
	@Override
	public void displayScoreBoard() {
		System.out.println("The jobs completed: ");
		// TODO
		// for (int i = 0; i < completedJobs.size(); i++) {
		// System.out.println("Job Name: " + completedJobs.get(i).getJobName());
		// System.out.println("Points earned for this job: " + completedJobs.get(i).getPoints());
		// System.out.println("--------------------------------------------");
		// }
		Iterator<Job> iterator = completedJobs.iterator();
		while (iterator.hasNext()) {
			/*
			 * the reason why i create tempJob is bc first two print statement both need access to
			 * the node; however if i call iterator.next() twice, the second time will point to next
			 */
			Job tempJob = iterator.next();
			System.out.println("Job Name: " + tempJob.getJobName());
			System.out.println("Points earned for this job: " + tempJob.getPoints());
			System.out.println("--------------------------------------------");
		}
	}

}
