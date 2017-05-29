
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

// Some notes:
// always remember header node is not a node
// JobList do not need <Job> is bc this class will only handle Job type no more <E>

/**
 * The container containing multiply ListNodes storing all active jobs. This implements ListADT
 * interface with all its methods. 
 * 
 * @author Team 57 import teamName
 */
public class JobList implements ListADT<Job> {
	/**
	 * Integer for storing num items in the list
	 */
	private int numItems;
	/**
	 * Listnode pointer pointing to first node in the list
	 */
	private Listnode<Job> head;

	/**
	 * Constructor for instantiating head pointing to header node and numItems to 0
	 * 
	 */
	public JobList() {
		head = new Listnode<Job>(null);
		numItems = 0;
	}

	/**
	 * Create an Iterator pointing to head
	 * 
	 * @return Iterator pointing to head
	 */
	@Override
	public Iterator<Job> iterator() {
		return new JobListIterator<Job>(head);
	}

	/**
	 * Adds an item at the end of the list
	 * 
	 * @param item
	 *            an item to add to the list
	 * @throws IllegalArgumentException
	 *             if item is null
	 */
	@Override
	public void add(Job item) throws IllegalArgumentException {
		if (item == null)
			throw new IllegalArgumentException();
		Listnode<Job> cur = head;
		while (cur.getNext() != null)
			cur = cur.getNext();
		cur.setNext(new Listnode<Job>(item));
		numItems++;
	}

	/**
	 * Add an item at any position in the list
	 * 
	 * @param item
	 *            an item to be added to the list
	 * @param pos
	 *            position at which the item must be added. Indexing starts from 0
	 * @throws IllegalArgumentException
	 *             if item is null
	 * @throws IndexOutOfBoundsException
	 *             if pos is less than 0 or greater than size() - 1
	 */
	@Override
	public void add(int pos, Job item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (pos < 0 || pos > size() - 1)
			throw new IndexOutOfBoundsException();
		Listnode<Job> cur = head;
		for (int i = 0; i < pos; i++)
			cur = cur.getNext();
		cur.setNext(new Listnode<Job>(item, cur.getNext())); // will do cur.getNext() first
		numItems++;
	}

	/**
	 * Check if a particular item exists in the list
	 * 
	 * @param item
	 *            the item to be checked for in the list
	 * @return true if value exists, else false
	 * @throws IllegalArgumentException
	 *             if item is null
	 */
	@Override
	public boolean contains(Job item) {
		if (item == null)
			throw new IllegalArgumentException();
		Listnode<Job> cur = head.getNext();
		while (cur != null) {
			if (cur.getData().equals(item))
				return true;
			cur = cur.getNext();
		}
		return false;
	}

	/**
	 * Returns the position of the item to return
	 * 
	 * @param pos
	 *            position of the item to be returned
	 * @throws IndexOutOfBoundsException
	 *             if position is less than 0 or greater than size() - 1
	 */
	@Override
	public Job get(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > size() - 1)
			throw new IndexOutOfBoundsException();
		Listnode<Job> cur = head.getNext(); // cur point to first elem, index 0
		// every loop means every increment one times
		for (int i = 0; i < pos; i++)
			cur = cur.getNext();
		return cur.getData();
	}

	/**
	 * Returns true if the list is empty
	 * 
	 * @return value is true if the list is empty else false
	 */
	@Override
	public boolean isEmpty() {
		return (numItems == 0);
	}

	/**
	 * Removes the item at the given positions
	 * 
	 * @param pos
	 *            the position of the item to be deleted from the list
	 * @return returns the item deleted
	 * @throws IndexOutOfBoundsException
	 *             if the pos value is less than 0 or greater than size() - 1
	 */
	@Override
	public Job remove(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > size() - 1)
			throw new IndexOutOfBoundsException();
		Listnode<Job> cur = head;
		for (int i = 0; i < pos; i++)
			cur = cur.getNext();
		Listnode<Job> returnNode = cur.getNext();
		cur.setNext(cur.getNext().getNext());
		numItems--;
		return returnNode.getData();
	}

	/**
	 * Returns the size of the singly linked list
	 * 
	 * @return the size of the singly linked list
	 */
	@Override
	public int size() {
		return numItems;
	}

}
