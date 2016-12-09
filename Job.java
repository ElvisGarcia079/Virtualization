

public class Job {
	//Since Schedulers purpose are to run multiple jobs so that the CPU doesn't remain idle it's important to define what the jobs
	//being passed in exactly are.
	
	private int burstTime; // data that represents amount of time processes need to complete
	private int waitingTime; // data that represents the difference between the burstTime and the arrival time
	private int turnAroundTime; 
	private int jobId; 
	//private static int counter;
	
	//Constructor for instantiating objects of this class.
	//Parameters passed in will represent variables in the class.
	public Job(int burstTime, int waitingTime, int turnAroundTime, int jobId){
		this.burstTime = burstTime;
		this.waitingTime = waitingTime;
		this.turnAroundTime = turnAroundTime;
		this.jobId = jobId;
		//counter++;
	}
	
	//Getters and Setter to set data more simply, and retrieve simply in other classes
	
	public void setBurstTime(int bt){
		burstTime = bt; //I can set any value in the parameter to the value of burstTime
	}
	
	public void setWaitingTime(int wt){
		waitingTime = wt;
	}
	
	public void setTurnAroundTime(int tat){
		turnAroundTime = tat;
	}
	
	public void setJobId(int id){
		jobId = id;
	}
	
	public int getBurstTime(){
		return burstTime;
	}
	
	public int getWaitingTime(){
		return waitingTime; 
	}
	
	public int getTurnaroundTime(){
		return turnAroundTime;
	}
	
	public int getJobId(){
		return jobId;
	}


	
}




