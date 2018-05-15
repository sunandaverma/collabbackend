package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.domain.Job;
import com.niit.collaboration.domain.JobApplication;

public interface JobDAO 
{
	public boolean saveJob(Job job);
	public boolean updateJob(Job job);
	public Job getJob(int jobid);
	public List<Job> jobList();
	public List<Job> jobList(char jobstatus);
	public boolean isJobOpened(int jobid);
	
	//for applying to the particular job
	
	public boolean saveJobApplication(JobApplication jobApplication);
	public boolean update(JobApplication jobApplication); // if admin needs to change the status of jobapplication (accept/reject/call for interview)
	public List<JobApplication> list(int jobid);	// for fetching list of total jobapplications for a particular job based on job id  by user
	public List<JobApplication> list(int jobid, char jobstatus);
	
	
	// public  boolean isJobAlreadyApplied(String email, int jobid);   //user applied for job so user cannot be deleted
	     					//changed to below function
	public  JobApplication get(String email, int jobid); //if job is aleady applied it wil return the job application else will return null
	public List<JobApplication> userAppliedJobList(String email);
	public Job getjobstatus(int jobid,char jobstatus); // admin/user wants to know whether the job existed with particular status or not   
	
	
	
	
	
	
	
	
		/*public boolean saveJob(Job job);// create a new job
		public boolean updateJob(Job job);  //admin may update the job details
		
		
		//admin will not delete the job
		//once the job is closed, admin will change
		//status - F/C
		
		public  Job  getJob(int jobid);    //fetch a particular job
		//fetch a particular JobApplication
		public List<Job> jobList();   //fetch all jobs
		public List<Job> list(char jobstatus);   //fetch all the jobs based on status
		public boolean   isJobOpened(int jobid);
		
		
		
		///job application
		public  boolean    saveJobApplication(JobApplication jobApplication);    //apply for a particular job
		public  boolean    updateJobApplication(JobApplication jobApplication);   //Admin can reject/accept/call for interview
		public  List<JobApplication> jobApplicationList(int jobid);   //Admin want to know the list of user those applid
		                                                              //for particular job
		
		public  List<JobApplication> list(int jobid,char status);      //Admin want fetch all the details of particular
		                                                               //job based on status
		
		public  boolean isJobAlreadyApplied(String email, int jobid);   //user applied for job so user cannot be deleted
*/}
