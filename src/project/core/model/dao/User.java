package project.core.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String userName;
	private String email;
	private String password;
	private String role = "user";
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(int id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	

}
