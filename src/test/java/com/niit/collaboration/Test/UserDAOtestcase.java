package com.niit.collaboration.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.domain.User;


public class UserDAOtestcase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static User user;
	
	@Autowired
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void initialize()
	{
	context =new AnnotationConfigApplicationContext();
	context.scan("com.niit.collaboration");
	context.refresh();
	user=(User) context.getBean("user");
	userDAO=(UserDAO) context.getBean("userDAO");
	
	}
	
	@Test
	public void userAddTest()
	{
		user.setEmail("sunnu@gmail.com");
		user.setName("sunanda");
		user.setPassword("1234567");
		user.setMobile("9988776799");
		
		assertEquals("user adding test case",true, userDAO.saveUser(user));
	}

}
