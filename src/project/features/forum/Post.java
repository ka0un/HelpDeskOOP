package project.features.forum;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Post {
	private int post_id;
	private int theThread;
	private String user;
	private String content;
	private LocalDateTime created_at;
	
	
	
	public Post(int threadId, String user, String content, LocalDateTime created_at) {
		super();
		this.theThread = threadId;
		this.user = user;
		this.content = content;
		this.created_at = created_at;
	}

	

	public Post(int post_id, int theThread, String user, String content, LocalDateTime created_at) {
		super();
		this.post_id = post_id;
		this.theThread = theThread;
		this.user = user;
		this.content = content;
		this.created_at = created_at;
	}



	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getTheThread() {
		return theThread;
	}

	public void setTheThread(int theThread) {
		this.theThread = theThread;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	
	
	
	
	
	
}
