package com.zhouyunlu.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="comment_table")

public class Comment implements java.io.Serializable{
	private static final long serialVersionUID = 6666646538881797793L;
	
	@Id
	@GeneratedValue
	@Column(name="comment_id")
	private long commentId;
	
	
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="user_name")
	private String userName;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="productID")
	private Product product;
	
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="comment_time")
	private String commentTime;
	
	public Comment(){
		
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public Product getProduct() {
		return product;
	}

	
	public void setProduct(Product product) {
		this.product = product;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	
	
}
