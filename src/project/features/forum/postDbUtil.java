package project.features.forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class postDbUtil {
	private DataSource dataSource;

    public postDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void close(Connection myConn, Statement mystmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }
            if (mystmt != null) {
                mystmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void addPost(Post newPost) throws SQLException {
		Connection myConn = null;
        PreparedStatement myStmt = null;
      //System.out.print("d");

        try {
			try {
			    // get db connection
			    myConn = dataSource.getConnection();
			    //post_id, thread_id, content, created_at, user_email
			    // create SQL for insert
			    String sql = "INSERT INTO posts"
			    		+ " (thread_id, content, user_email, created_at) "
			    		+ "VALUES"
			    		+ " (?, ?, ?, ?)";

			    myStmt = myConn.prepareStatement(sql);

			    // Get the created_at value as LocalDateTime
			    LocalDateTime createdAt = newPost.getCreated_at();

			    // Convert LocalDateTime to Timestamp for database
			    Timestamp timestamp = Timestamp.valueOf(createdAt);

			    // Set the param values for the thread
			    myStmt.setInt(1, newPost.getTheThread());
			    myStmt.setString(2, newPost.getContent());
			    myStmt.setString(3, newPost.getUser());
			    myStmt.setTimestamp(4, timestamp); // Correct way to set datetime/timestamp
			   

			    // Execute SQL insert
			    myStmt.execute();
			    //System.out.print("d");
			   
			} catch (Exception e) {
			    e.printStackTrace();
			} finally {
			    // Clean up JDBC objects
			    close(myConn, myStmt, null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	

	 List<Post> getPosts(String threadId) throws Exception {
		System.out.println("Retrieving posts1...");
		List<Post> posts = new ArrayList<>();
		
		int ThreadId = Integer.parseInt(threadId);
		Connection myConn = null;
		Statement mystmt = null;
		ResultSet myRs = null;
try {
			
			myConn = dataSource.getConnection();
			System.out.println("Retrieving posts2...");
			
			String sql = "select * from posts "
					+ "where thread_id = "+ ThreadId 
					+ "order by created_at desc;";
			
			mystmt= myConn.createStatement();
			
			myRs = mystmt.executeQuery(sql);
			
			while(myRs.next()) {
				int post_id = myRs.getInt("post_id");
				
				String user_email = myRs.getString("user_email");
				String content = myRs.getString("content");
				Timestamp created_at = myRs.getTimestamp("created_at");
				
				Post tempPost = new Post(post_id, ThreadId, user_email,
						content, created_at.toLocalDateTime());
				
				posts.add(tempPost);				
			}
			
			
			return posts;
		}
		finally {
			close(myConn, mystmt, myRs);
			
		}		
		
	}
		
	}

