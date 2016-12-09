/*Output must contain: 
	Average Time of: 
		a. RR : avgTimeRR
		b. FIFO: avgTimeFIFO
*/

/* Elvis Garcia and Chayanne Paulino*/

import java.util.*;
import java.io.*;


public class Scheduler{
	
	public static ArrayList<Job> createJobs(int num){
		ArrayList<Job> jobs = new ArrayList<Job>(); // Create list for jobs to be placed in
		Random rand = new Random(); // Generate random object 
		for(int i = 0;i < num; i++){	
			//FOR EACH JOB...
			Job currentJob = new Job(rand.nextInt(50) + 1,0,0, i+1); // Generate the job with a random burst time between 1-50ms. 
			jobs.add(currentJob); // Add current job to list of jobs.		
		}
		return jobs; // Return newly created list of jobs 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Create interface for users to input on the screen and an object that can read what was inputted. 
		System.out.println("Enter the number of processes to be created");
		int noOfProcess = Integer.parseInt(br.readLine()); // Turn input into an integer 
		ArrayList<Job> arr = createJobs(noOfProcess);  // Create as job list with as many jobs as was passed in by the user.
		ArrayList<Job> arrCopy = new ArrayList<Job>(); // create new list of jobs.
		
		//I need to duplicate the array to store the original time burst which I will be changing later
		System.out.println("Printing the burst time of the processes");
		for(int i = 0;i< arr.size();i++){
			//FOR EACH JOB (In new list of jobs.)
			Job currentJob = new Job(arr.get(i).getBurstTime(),0,0, i+1); //get info from original job list. 
			arrCopy.add(currentJob); //add copy of that job to new job list.
			System.out.println(arr.get(i).getBurstTime());
		}
		System.out.println("================================================================="); // Split results in output with input.
		
		System.out.println("Enter time interval for round robin:");
		int timeQuantum = Integer.parseInt(br.readLine());	// Set the time quantum passed in by user
		
		//Create RR and compute the jobs in the list
		RoundRobin rr = new RoundRobin(arr,timeQuantum); 
		rr.compute(arrCopy);
		
		//Create FIFO and compute the jobs in the list
		FIFO fifo = new FIFO(arr); // 
		fifo.compute(arrCopy);
		
		System.out.println("Values will be compared below...");
		System.out.println("VALUES FOR WAITING TIME:");
		System.out.println("Round Robin Average waiting time: " + rr.avgWaiting);
		System.out.println("FIFO Average waiting time: " + fifo.avgWaitingTime);
		System.out.println("VALUES FOR TURN AROUND TIME:");
		System.out.println("Round Robin Average Turnaround time: "+ rr.avgTurnAround);
		System.out.println("FIFO Average Turnaround time: "+fifo.avgTurnAroundTime);
		
		
		
		
	}
}