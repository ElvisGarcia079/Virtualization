import java.util.*;
import java.io.*;


public class FIFO{
	ArrayList<Job> jobsFIFO; // Create job list for FIFO to use.
	double avgWaitingTime;
	double avgTurnAroundTime;
	
	public FIFO(ArrayList<Job> jobs){
		jobsFIFO = new ArrayList<Job>(); //Initialize new ArrayList of jobs for when an object is created.
		
		for(int i = 0; i < jobs.size(); i++){
			Job currentJob = new Job(jobs.get(i).getBurstTime(),0,0, i+1); // The job object created at the specific index
			jobsFIFO.add(currentJob); //Add that job, to the list of jobs.
			
		}
		
		avgWaitingTime = 0.0; // initialize to 0
		avgTurnAroundTime = 0.0; // initialize to 0
	}
	
	public void compute(ArrayList<Job> jobList){
		System.out.println("\nFIFO Schedule begins: ");
		int tempTurnAround = 0; // initialize to 0 for start.
		
		for(int i = 0 ; i < jobsFIFO.size(); i++){
			//FOR EACH JOB...
			Job tempJob = jobsFIFO.get(i); // set the temporary job to the job at this index to work with
			System.out.println("In FIFO Processing Job Number: " + tempJob.getJobId()); 
			tempJob.setWaitingTime(tempTurnAround); // set the waiting time to the temporary turn around time. Will always be 0 on first run.
			tempJob.setTurnAroundTime(tempJob.getBurstTime()+tempJob.getWaitingTime()); // set the turn around time to the sum of the burst time and waiting time
			tempTurnAround += tempJob.getBurstTime(); // Increment the turn around time by the burst time of the job being worked with.
		}
		
		System.out.println("All jobs processed with FIFO\n");
		
		double totalWaitingTime = 0.0;
		double totalTurnAroundTime = 0.0;
		for(int i = 0; i < jobsFIFO.size(); i++){
			//FOR EACH JOB...
			Job temp = jobsFIFO.get(i); // Create a variable to store the current job object in the list being worked with...
			totalTurnAroundTime += temp.getTurnaroundTime(); // increment the total turn around time by the turn around time computed above.
			totalWaitingTime += temp.getWaitingTime(); // increment the total waiting time by the waiting time computed above.
		}
		
		avgTurnAroundTime = totalTurnAroundTime/jobsFIFO.size(); // Calculate the average by dividing the total number, by the amount of jobs.
		avgWaitingTime = totalWaitingTime/jobsFIFO.size(); // Calculate the average by dividing the total number by the amount of jobs.
	}
}


