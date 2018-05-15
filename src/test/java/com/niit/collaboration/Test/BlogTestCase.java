package com.niit.collaboration.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.DAO.BlogDAO;
import com.niit.collaboration.domain.Blog;


public class BlogTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Blog blog;
	
	@Autowired
	private static BlogDAO blogDAO;
	
	@BeforeClass
	public static void initialize()
	{
	context =new AnnotationConfigApplicationContext();
	context.scan("com.niit.collaboration");
	context.refresh();
	blog=(Blog) context.getBean("blog");
	blogDAO=(BlogDAO) context.getBean("blogDAO");
	
	}
	
	@Test
	public void blogAddTest()
	{
		blog.setEmail("sunanda@gmail.com");
		blog.setBlogdescription("job job");
		blog.setBlogremarks("K");
		blog.setBlogtitle("BLOG");
		
		assertEquals("blog adding test case",true, blogDAO.saveBlog(blog));
	}


}
