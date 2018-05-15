package com.niit.collaboration.DAOImplement;

import java.util.Date;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.DAO.JobDAO;
import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.domain.Job;
import com.niit.collaboration.domain.JobApplication;

@Transactional
@Repository("jobDAO")
public class JobDAOimpl implements JobDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;
	
	private int getMaxJobID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) sessionFactory.getCurrentSession().createQuery("select max(jobid) from Job").uniqueResult();
		} catch (Exception e) {
			
			e.printStackTrace();
			return 100;
		}

		return maxValue;
	}
	
	public boolean saveJob(Job job) {
		try {
			job.setJobid(getMaxJobID() + 1);
			job.setPosted_date(new Date());
			job.setJobstatus('N');
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public Job getJob(int jobid) {
		return (Job) sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobid", jobid)).uniqueResult();
	}

	public List<Job> jobList() {
		return sessionFactory.getCurrentSession().createQuery("from Job").list();
	}

	public List<Job> jobList(char jobstatus) {
		return sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobstatus", jobstatus)).list();
	}
	
	private int getMaxJobapplicationID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) sessionFactory.getCurrentSession().createQuery("select max(jobapplicationid) from JobApplication").uniqueResult();
		} catch (Exception e) {
			
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}	
	
	public boolean isJobOpened(int jobid) {
		Job job = (Job) sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobid", jobid)).uniqueResult();

		if (job != null && job.getJobstatus() == 'N') {
			return true;
		}
		return false;
	}

	public boolean saveJobApplication(JobApplication jobApplication) {
		try {
				if(isJobOpened(jobApplication.getJobid()) == false) {
					return false;
				}
			
				// if you already applied, you can not apply again
				if(get(jobApplication.getEmail(), jobApplication.getJobid())!=null) {
					return false;
				}
			
				//if user does not exist, you can not apply
				if(userDAO.getUser(jobApplication.getEmail()) == null)
				{
					return false;
				}
			
				//if the job does not exist, you can not apply
				if(getJob(jobApplication.getJobid())==null)
				{
					return false;
				}

				jobApplication.setJobapplicationid(getMaxJobapplicationID() + 1);
				jobApplication.setJobapplicationstatus('N');
				jobApplication.setApplied_date(new Date());
	
				sessionFactory.getCurrentSession().save(jobApplication);
				return true;
				
				} catch (HibernateException e) {
					
					e.printStackTrace();
					return false;
				}
	}

	public boolean update(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public List<JobApplication> list(int jobid) {
		return sessionFactory.getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("jobid", jobid)).list();
	}

	public List<JobApplication> list(int jobid, char jobstatus) {
		return sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobstatus", jobstatus)).add(Restrictions.eq("jobid", jobid)).list();
	}

	/**
	 * This method will return jobapplication if the job already applied with this emaild.
	 * else, return false
	 */

	public JobApplication get(String email, int jobid) {

		//select * from JobApplication where emailID = ? and jobID = ?
	return	 (JobApplication) sessionFactory.getCurrentSession()
				.createCriteria(JobApplication.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("jobid", jobid)).uniqueResult();

		
	}

	public List<JobApplication> userAppliedJobList(String email) {
		return sessionFactory.getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("email", email)).list();
	}

	public boolean deleteJob(int jobid) {
		try {
			sessionFactory.getCurrentSession().delete(getJob(jobid));
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	
	//this method will return job if the job exist with this jobid and status
	public Job getjobstatus(int jobid, char jobstatus) {
		
		return (Job) sessionFactory.getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobid", jobid))
		 																		.add(Restrictions.eq("jobstatus", jobstatus)).uniqueResult();
	}
	
	
}





/*@Repository("jobDAO")
@Transactional
public class JobDAOimpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	*//**
	 * return max job id of all the records if the records are exist else return 100
	 * 
	 * @return
	 *//*
	private int getMaxJobID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) getCurrentSession().createQuery("select max(jobid) from Job").uniqueResult();
		} catch (Exception e) {
			
			e.printStackTrace();
			return 100;
		}

		return maxValue;
	}

	private int getMaxJobapplicationID() {
		int maxValue = 100;
		try {
			maxValue = (Integer) getCurrentSession().createQuery("select max(jobapplicationid) from JobApplication").uniqueResult();
		} catch (Exception e) {
		
			e.printStackTrace();
			return 100;
		}

		return maxValue;
	}

	public boolean saveJob(Job job) {
		try {
			job.setJobid(getMaxJobID() + 1);
			job.setPosted_date(new Date());
			job.setJobstatus('N');
			getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(Job job) {
		try {
			getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public Job getJob(int jobid) {
		return (Job) getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobid", jobid)).uniqueResult();
	}

	public List<Job> jobList() {
		return getCurrentSession().createQuery("from Job").list();
	}

	public List<Job> list(char jobstatus) {
		return getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobstatus", jobstatus)).list();

	}

	public boolean saveJobApplication(JobApplication jobApplication) {
		System.out.println("jobapplication saving method");
		try {
			if (!isJobOpened(jobApplication.getJobid())) 
			{
				System.out.println("job not opened");
				return false;
			}
			// if you already applied, you can not apply again
			if (isJobAlreadyApplied(jobApplication.getEmail(), jobApplication.getJobid())) 
			{
				System.out.println("already applied");
				return false;
			}
			
			//if user does not exist, you can not apply
			
			if(userDAO.getUser(jobApplication.getEmail())==null)
			{
				System.out.println("user not existed");
				return false;
			}
			
			//if the job does not exist, you can not apply
			if(getJob(jobApplication.getJobid())==null)
			{
				System.out.println("job not existed");
				return false;
			}

			jobApplication.setJobapplicationid(getMaxJobapplicationID() + 1);
			jobApplication.setJobapplicationstatus('N');
			jobApplication.setApplied_date(new Date());
			getCurrentSession().save(jobApplication);
			return true;
		} catch (Exception e) {
		
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			getCurrentSession().update(jobApplication);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public List<JobApplication> jobApplicationList(int jobid) {
		return getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("id", jobid)).list();

	}

	public List<JobApplication> list(int jobid, char status) {
		return getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("id", jobid))
				.add(Restrictions.eq("status", status)).list();

	}

	*//**
	 * This method will return true, if the job with id exist and status is open.
	 * else return false.
	 *//*
	public boolean isJobOpened(int jobid) {
		Job job = (Job) getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("jobid", jobid)).uniqueResult();

		if (job != null && job.getJobstatus() == 'N') {
			return true;
		}

		return false;

	}

	*//**
	 * This method will return true if the job already applied with this emaild.
	 * else, return false
	 *//*

	public boolean isJobAlreadyApplied(String email, int jobid) {

		//select * from JobApplication where emailID = ? and jobID = ?
		JobApplication jobApplication = (JobApplication) getCurrentSession()
				.createCriteria(JobApplication.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("jobid", jobid)).uniqueResult();

		if (jobApplication == null) {
			return false;
		}
		return true;

	}
*/
	


	


