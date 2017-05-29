
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
import java.util.NoSuchElementException;

// Some notes:
// direct access: pass linednode
// indirect access: pass linkedlist
// Iterator's next always refer to this elem

/**
 * This class are used to iterate through a chain of nodes. The iterator has direct access to the
 * JobList’s chain of nodes.
 *
 * @author Team 57 import teamName
 */
public class JobListIterator<E> implements Iterator<E> {
	/**
	 * Listnode pointer pointing to currently iterating node in the list
	 */
	private Listnode<E> curNode;

	/**
	 * Constructor for instantiating curNode pointer to Listnode header
	 * 
	 * @param header
	 *            pointer pointing to first node in the list; not header node
	 */
	public JobListIterator(Listnode<E> header) {
		curNode = header.getNext();
	}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		return curNode != null;
	}

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	@Override
	public E next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException();
		E returnData = curNode.getData();
		curNode = curNode.getNext();
		return returnData;
	}
}
