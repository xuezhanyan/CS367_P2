import java.util.*;

public class Test_JobList {
	private static int passed = 0; // keep count of each passed test
	private static int failed = 0; // keep count of each failed test
	
	public static void main(String[] args) {

		//JobList object used to test all implemented method	
		JobList test = new JobList();
		// Job object to test all implemented methods
		Job testJob = new Job("Job One", 100, 10);
		//Assign a position number 
		int pos = 0;

		testAdd(test, testJob);
		test.add(new Job("Job02", 100, 10));
		test.add(new Job("Job03", 100, 10));
		Job job01 = new Job("Job01", 100, 10);
		test.add(job01);
//		testRemove(test,testJob,0);

		test_contains( test,job01 );
		//A variable to test the addAtPos
		int position =4;
//		test_addAtPos(position);
//		test_getAtPos(test,testJob,pos); 
//		test_IsEmpty(test);  
//		test_Size();
		test_iterator();

		//print out the number of passed and failed tests
		System.out.println("Passed " + passed + " tests.");
		System.out.println("Failed " + failed + " tests.");

	}

	public static void testAdd(JobList test, Job testJob)
	{
		//implement the add method
		test.add(testJob);
		// If testJob is with in the JobList, test passed
		if (test.contains(testJob))
			System.out.println( "testAdd passed.");
		else
			System.out.println( "testAdd failed. It should contain " + testJob.getJobName());
	}

	private static void testRemove(JobList test, Job testJob, int pos)
	{
		//implement the remove method
		test.remove(pos);

		//if the specific position still contains the test job
		//if not print out error message
		if (test.get(pos).getJobName().equals(testJob.getJobName()))
		{
			System.out.println( "JobList failed, it should remove " + testJob.getJobName() );
			failed ++;
		}
		else{
			passed ++;
			System.out.println( "JobList passed" ); 
		}
	}

	private static void test_addAtPos(int position){
		//initialize a new joblist with 10 elements in it
		JobList job = new JobList();
		for(int i = 0; i < 10; i++){
			job.add(new Job("J0",30,40));
		}
		
		//implement the addAtPos method
		job.add(position,new Job("J1",30,50));
		
		//check if the nth position equals to 
		if(job.get(position).getJobName().equals("J1")){
			passed++;
			System.out.println("test_addATPos passed");
		}
		//print out error message
		else{
			failed++;
			System.out.println("test_addATPos failed."
					+ "J1 did not add at position ");
		}
	}


	private static void test_contains(JobList test,Job testJob){
		//check if job01 contains in jobList test
		if(test.contains(testJob)){
			passed++;
		}
		//print out error message
		else{
			System.out.println("test_contains failed.\nit should have "+ 
		testJob.getJobName() );
			failed++;
		}


	}
	private static void test_getAtPos(JobList test, Job testjob, int pos){
		//check if the nth position's job equals to specific job
		if(test.get(pos).getJobName().equals(testjob.getJobName())){
			passed++;
			System.out.println("test_getAtPos passed");
		}
		//print out error message
		else{
			System.out.print("test_getAtPos failed");
			System.out.println("test getAtPos should be " + testjob.getJobName()+ 
					" instead of "+test.get(pos).getJobName());
			failed++;
		}
		//a variable to test whether the size handles indexout of bounds exception
		boolean testforsize =false;
		//check the condition when get at position size
		try{
			test.get(test.size());
			testforsize = false;
		} catch (IndexOutOfBoundsException e) {
			testforsize = true;
		}
		//print out error message
		if (testforsize==false)
			System.out.println("test_getAtPos failed. JobList should throw "
					+ "an IndexOutOfBounds exception.");

	}
	// 
	private static void test_IsEmpty(JobList test){
		//check if test is empty
		if(test.isEmpty() != true){
			passed++;
			System.out.println("test_IsEmpty passed");
		}
		//print out error message
		else{
			failed++;
			System.out.println("it should contain items");
		}
	}

	private static void test_Size(){
		JobList testsize = new JobList();
		//initialize a jobList with a random number n
		Random random = new Random();
		int n = random.nextInt(10);
		//add n new job to the joblist
		for (int i =0; i< n; i++){
			testsize.add(new Job("testnode",100,10));
		}
		//check whether the size of joblist equals n
		//if not print out error message
		if(testsize.size()!= n){
			int wrongNum = testsize.size();
			System.out.print("test_Size failed");
			System.out.println("test size() should be " + n+ " instead of "+wrongNum );
			failed++;
		}
		//if test has n jobs, it passes the test.
		else{
			passed++;
			System.out.println("test_Size passed");
		}
	}

	private static void test_iterator(){
		JobList job = new JobList();
		job.add(new Job("J0",20,40));
		job.add(new Job("J1",20,40));
		job.add(new Job("J2",20,40));
		Iterator<Job> itr = job.iterator();
		itr.next();
		itr.next();//iterate to the last index
		//check iterator.next() method
		if(itr.next().getJobName().equals(job.get(2).getJobName())){
			passed++;
			System.out.println("test_iterator_next passed");
		}
		//print out error message
		else{
			failed++;
			System.out.println("test_iterator_next failed");
		}
		//check iterator.hasNext method
		String name ="";
		while(itr.hasNext()){
			name = itr.next().getJobName();//name the jobName of last index String
		}
		if(name.equals("J2")){
			passed++;
			System.out.println("test_iterator_hasNext passed");
		}else{
			failed++;
			System.out.println("test_iterator_hasNext failed");
		}
	}
}