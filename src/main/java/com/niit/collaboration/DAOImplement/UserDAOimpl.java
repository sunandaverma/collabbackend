package com.niit.collaboration.DAOImplement;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.domain.User;

@Repository("userDAO")
@Transactional
public class UserDAOimpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public boolean saveUser(User user) {
		try {
			
				if(user.getRole()==null || user.getRole()==' ')
				{
					user.setRole('S');
				}
				user.setStatus('N');
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e) {
		return false;
	}
		
	}

	public boolean deleteUser(String email) {
		try {
			sessionFactory.getCurrentSession().delete(getUser(email));
			return true;
		}
		catch(Exception e) {
		return false;
	}
		}
	

	public User getUser(String email) {
		return sessionFactory.getCurrentSession().get(User.class,email);
	}

	public List<User> userList() {
		
		return sessionFactory.getCurrentSession().createQuery("from User").list();
		
	}

	public User validateUser(String email, String password) {
		
		User user;
		user= (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				                                                   .add(Restrictions.eq("email",email))
				                                                   .add(Restrictions.eq("password",password)).uniqueResult();
		if(user==null)
		{
			return null;
		}
		else
			return user;
		
	}
}
