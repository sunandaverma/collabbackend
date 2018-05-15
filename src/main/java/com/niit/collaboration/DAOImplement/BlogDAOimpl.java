package com.niit.collaboration.DAOImplement;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.DAO.BlogDAO;
import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.domain.Blog;
import com.niit.collaboration.domain.User;

@Transactional
@Repository("blogDAO")
public class BlogDAOimpl implements BlogDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Blog blog;

	public boolean saveBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public Blog getBlog(int blogid) {
		
		return sessionFactory.getCurrentSession().get(Blog.class,blogid);	
	}

	public List<Blog> blogList() {
		
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}
	
	
}
