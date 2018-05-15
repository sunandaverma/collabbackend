package com.niit.collaboration.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="c_blogcomment")
public class BlogComment {
	
	@Id
	private int blogcommentid;
	private String email;
	private int  blogid;
	private String blogcommentcomments;
	private Date commentedDate;
	
	
	public int getBlogcommentid() {
		return blogcommentid;
	}
	public void setBlogcommentid(int blogcommentid) {
		this.blogcommentid = blogcommentid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getBlogcommentcomments() {
		return blogcommentcomments;
	}
	public void setBlogcommentcomments(String blogcommentcomments) {
		this.blogcommentcomments = blogcommentcomments;
	}
	public Date getCommentedDate() {
		return commentedDate;
	}
	public void setCommentedDate(Date commentedDate) {
		this.commentedDate = commentedDate;
	}

	
}


