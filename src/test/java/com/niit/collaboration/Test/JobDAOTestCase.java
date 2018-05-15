package com.niit.collaboration.Test;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.DAO.JobDAO;
import com.niit.collaboration.domain.Job;
import com.niit.collaboration.domain.JobApplication;




public class JobDAOTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Job job;
	
	@Autowired
	private static JobDAO jobDAO;
	
	@Autowired
	private static JobApplication jobApplication;
	
	@BeforeClass
	public static void initialize()
	{
	context =new AnnotationConfigApplicationContext();
	context.scan("com.niit.collaboration");
	context.refresh();
	job=(Job) context.getBean("job");
	jobDAO=(JobDAO) context.getBean("jobDAO");
	jobApplication=(JobApplication) context.getBean("jobApplication");
	
	}
	
	@Test
	public void jobAddTest()
	{
		job.setJobqualification("B.Tech");
		job.setJobdescription("web developing using java");
		job.setJobsalary(800000);
		job.setJobtitle("web developer");
		job.setNo_of_openings(5);
		
		assertEquals("job adding test case",true, jobDAO.saveJob(job));
	}
	
	@Test
	public void jobApplicationAddTest()
	{
		jobApplication.setEmail("sunnu@gmail.com");
		jobApplication.setJobid(103);
		
		assertEquals("job application adding test case", true, jobDAO.saveJobApplication(jobApplication));
	}

}
