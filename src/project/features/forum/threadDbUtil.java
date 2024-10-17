package project.features.forum;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class threadDbUtil {
    private DataSource dataSource;

    public threadDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<thread> getThreads() throws Exception {
        List<thread> threads = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            
            // SQL query to retrieve threads
            String sql = "SELECT * FROM thread";
            
            myStmt = myConn.prepareStatement(sql);
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("thread_id");
                String title = myRs.getString("title");
                String content = myRs.getString("content");
                String user = myRs.getString("user_email");
                Timestamp created_at = myRs.getTimestamp("created_at");
                String status = myRs.getString("status");
                
                thread tempThread = new thread(id, title, user, created_at.toLocalDateTime(), status, content);
                threads.add(tempThread);
                
                
            }
            
            return threads; // Return the list of threads
        } 
        catch (Exception e) {
            System.out.println(e);
            throw e; // Re-throw the exception for proper handling
        } 
        finally {
            close(myConn, myStmt, myRs); // Close resources
        }
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

    public void addThread(thread theThread) throws SQLException{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
			try {
			    // get db connection
			    myConn = dataSource.getConnection();

			    // create SQL for insert
			    String sql = "INSERT INTO thread"
			    		+ " (title, content, user_email, created_at, status) VALUES"
			    		+ " (?, ?, ?, ?, ?)";

			    myStmt = myConn.prepareStatement(sql);

			    // Get the created_at value as LocalDateTime
			    LocalDateTime createdAt = theThread.getCreated_at();

			    // Convert LocalDateTime to Timestamp for database
			    Timestamp timestamp = Timestamp.valueOf(createdAt);

			    // Set the param values for the thread
			    myStmt.setString(1, theThread.getTitle());
			    myStmt.setString(2, theThread.getContent());
			    myStmt.setString(3, theThread.getUser());
			    myStmt.setTimestamp(4, timestamp); // Correct way to set datetime/timestamp
			    myStmt.setString(5, theThread.getStatus());

			    // Execute SQL insert
			    myStmt.execute();
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
    
    public thread getThread(String theThreadId) throws Exception{
		thread theThread= null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		int ThreadId;
		
		try {
			ThreadId = Integer.parseInt(theThreadId);
			myConn = dataSource.getConnection();
			
			
			String sql = "select * from thread where thread_id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, ThreadId);

			myRs= myStmt.executeQuery();
			
			if(myRs.next()) {
				int id = myRs.getInt("thread_id");
	            String title = myRs.getString("title");
	            String content = myRs.getString("content");
	            String user = myRs.getString("user_email");
	            Timestamp created_at = myRs.getTimestamp("created_at");
	            String status = myRs.getString("status");
	            
	            //LocalDate created_at = myRs.getTimestamp("created_at");.toLocalDate();
	            

	            theThread = new thread(id,title, user, created_at.toLocalDateTime(), status, content);
				
				
			}else {
				throw new Exception("Could not find thread id: "+ThreadId);
			}
			
			return theThread;
			
					
		}
		
		finally {
			close(myConn, myStmt, myRs);
		
		}
	}

	public List<thread> viewThread(String user) throws Exception{
		List<thread> threads = new ArrayList<>();
		String user1 = user;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		
		
		
try {
			
			myConn = dataSource.getConnection();
			System.out.println("Retrieving posts2...");
			System.out.println(user);
			
			String sql = "SELECT thread_id, title, content FROM thread where user_email = ?;";

			
			myStmt= myConn.prepareStatement(sql);
			System.out.println("Retrieving posts3...");
			myStmt.setString(1, user1);
			
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				
				int id = myRs.getInt("thread_id");
				
				String title = myRs.getString("title");
				String content = myRs.getString("content");
				
				thread tempThread = new thread(id, title,content);
				
				threads.add(tempThread);				
			}
			
			
			return threads;
		}
		finally {
			close(myConn, myStmt, myRs);
			
		}	
		
	}

	

	public thread updateThread(String theThreadId, String title, String user, String content, String status,
			LocalDateTime created_at) throws Exception {
		
		
				
		
		return null;
	}

	public void updateThread(thread thread1) {
		thread theThread= thread1;
				Connection myConn = null;
				PreparedStatement myStmt = null;
				ResultSet myRs = null;
				try {
				
				myConn = dataSource.getConnection();
					
				
				String sql = "UPDATE thread set title=?, "
						+ "content=?, created_at=?, status=?;"
						+ "where thread_id=?";
				
				

			    // Convert LocalDateTime to Timestamp for database
			    Timestamp timestamp = Timestamp.valueOf(theThread.getCreated_at());
				myStmt = myConn.prepareStatement(sql);
				
				myStmt.setString(1, theThread.getTitle());
				myStmt.setString(2, theThread.getContent());
				myStmt.setTimestamp(3, timestamp);
				myStmt.setString(4, theThread.getStatus());
				myStmt.setInt(5, theThread.getId());
				
				 myStmt.executeUpdate();
				 
			} catch (Exception e) {
			    e.printStackTrace();
			} finally {
			    // Clean up JDBC objects
			    close(myConn, myStmt, null);
			}
				
				
		
	}

	public void deleteThread(String theThreadId) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
		
		myConn = dataSource.getConnection();
			
		
		String sql = "DELETE FROM thread WHERE thread_id=?;";
		
		myStmt = myConn.prepareStatement(sql);
		
		myStmt.setString(1, theThreadId);
		
		myStmt.execute();
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    // Clean up JDBC objects
		    close(myConn, myStmt, null);
		}
		
		
		
	}

	
	

	
}
