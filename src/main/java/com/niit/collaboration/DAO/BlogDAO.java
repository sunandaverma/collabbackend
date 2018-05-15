package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.domain.Blog;

public interface BlogDAO {

	public boolean saveBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public Blog getBlog(int blogid);
	public List<Blog>    blogList();
	
	//adming can accept/reject the blog.
	//we can use update(Blog blog) method.
	//comment on a particular blog.
	//one to many ->  N number of user can comment on.
	// a particular blog.
	
}
	
	
	
	
	
	
	
	
	
	
	
	



