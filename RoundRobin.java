
import java.util.*;

public class RoundRobin{
	ArrayList<Job> jobsRR; // Create object to store list of jobs.
	int timeQuantum; // The time splice for the Round Robin Scheduler
	double avgWaiting; 
	double avgTurnAround;
	
	public RoundRobin(ArrayList<Job> jobInput, int timeQuantum){
		jobsRR = new ArrayList<Job>(); // Create new list of jobs
		for(int i = 0; i < jobInput.size(); i++){
			//FOR EACH JOB...
			Job currentJob = new Job(jobInput.get(i).getBurstTime(),0,0, i+1); // Create new job object
			jobsRR.add(currentJob); //Add job object to list of jobs for Round Robin
		}
		this.timeQuantum = timeQuantum;
		avgTurnAround = 0;
		avgWaiting = 0;
	}
	public void compute(ArrayList<Job> arrCopy){
		
		int sumOfBurstTime = -1;
		System.out.println("Round Robin scheduler starts : ");
		while(sumOfBurstTime != 0){ // While there is still work to get done
			for(int i = 0; i < jobsRR.size(); i++){
				//FOR EACH JOB...
				Job temp = jobsRR.get(i); // Create new job object for new job
				int tempBurstTime = temp.getBurstTime(); // set burst time to the burst time of that new job 
				if(temp.getBurstTime() > 0){ //If this job still has work to do
					System.out.println("Processing job with ID : "+temp.getJobId());
				}
				
				if(temp.getBurstTime() > timeQuantum){ //If the work left is greater than the time quantum 
					temp.setBurstTime(temp.getBurstTime()-timeQuantum); //Update the burst time to the difference of burst time and the time quantum processed.
					for(int j = 0; j<jobsRR.size(); j++){
						//FOR EVERY OTHER JOB...
						if((j!=i) && jobsRR.get(j).getBurstTime()!= 0){ //If this job is not the job being worked on, and still has work to get done...
							jobsRR.get(j).setWaitingTime(jobsRR.get(j).getWaitingTime()+timeQuantum); // Add the quantum time for the job to the waiting time
						}
					}
				}
				else{
					for(int j = 0; j<jobsRR.size(); j++){ 
						//FOR EVERY OTHER JOB
						if((j!=i) && jobsRR.get(j).getBurstTime()!= 0){ //If it's not the job being worked on, and still has work to do...
							jobsRR.get(j).setWaitingTime(jobsRR.get(j).getWaitingTime()+tempBurstTime); //Add the burst time for the job to the waiting time
						}
						temp.setBurstTime(0); // Set the burst time to 0 again to work on next jobs if any...
					}
					
				}
			}
			
			sumOfBurstTime = 0;
			for(int k = 0; k < jobsRR.size(); k++){
				//FOR EACH JOB...
				sumOfBurstTime += jobsRR.get(k).getBurstTime(); // Increment burst times of all jobs
			}
				
			
		}
		System.out.println("All jobs processed");
		double totalWaitingTime =0.0;
		double totalTurnAroundTime =0.0;
//		
		for(int i = 0; i < jobsRR.size(); i++){
			//FOR EACH JOB...
			Job temp = jobsRR.get(i); // Create new job object for new job
			//set total turn around time for job, then get it and increment it by the waiting time of the job
			temp.setTurnAroundTime(arrCopy.get(i).getBurstTime()+temp.getWaitingTime()); 
			totalTurnAroundTime += temp.getTurnaroundTime();
			totalWaitingTime += temp.getWaitingTime();
		}
		
		avgTurnAround = totalTurnAroundTime/jobsRR.size(); // Get average by dividing total number by the number of jobs.
		avgWaiting = totalWaitingTime/jobsRR.size(); // Get average by dividing total number by the number of jobs. 
		
	}
}