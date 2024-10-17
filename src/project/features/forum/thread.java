package project.features.forum;

import java.time.LocalDateTime;

public class thread {
	private int id;
	private String title;
	private String user;
	private LocalDateTime created_at;
	private String status;
	private String content;
	
	
	
	

	public thread(String user) {
		super();
		this.user = user;
	}

	public thread(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public thread(int id, String title, String content) {
		super();
		this.id =id;
		this.title = title;
		this.content = content;
	}

	public thread(String title, String user, LocalDateTime created_at, String status,
			String content) {
		super();
		this.title = title;
		this.user = user;
		this.created_at = created_at;
		this.status = status;
		this.content = content;
	}
	
	
	
	public thread(int id, String title, String user, LocalDateTime created_at, String status, String content) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
		this.created_at = created_at;
		this.status = status;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}