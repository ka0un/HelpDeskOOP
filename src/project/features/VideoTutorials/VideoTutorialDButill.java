package project.features.VideoTutorials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideoTutorialDButill {
	
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;

    

	
	public static boolean insertVideo(String title , String videoId , String category) {
		
		try {
          
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "INSERT INTO video_tutorials (Title, YoutubeUrl, UploadDate, Category, Type) VALUES ('" + title + "', '" + videoId + "', NOW(), '" + category + "' ,'enable')";		    
		    int result = stmt.executeUpdate(sql);    // result can be 0 or 1
		    
		    if(result > 0) {
		    	isSuccess = true;
		    }
		    else {
		    	isSuccess = false;
		    }	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return isSuccess;
	}
	
	public static void updateViews(String id) {
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "UPDATE video_tutorials SET Views = Views + 1 WHERE  YoutubeUrl = '"+id+"'";
			int result = stmt.executeUpdate(sql);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<VideoTutorial> getVideoDetails(){
		
		ArrayList<VideoTutorial> tutorial = new ArrayList<>();
		
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "select * from video_tutorials where type ='enable' ";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String videoId = rs.getString(3);
				int views = rs.getInt(4);
				Timestamp uploadDate = rs.getTimestamp(5);
				String category = rs.getString(6);
				String type = rs.getString(7);
				
				VideoTutorial vtutorial = new VideoTutorial(id, title, videoId, category, views, uploadDate,type);
				tutorial.add(vtutorial);
					
			}
					
		}catch(Exception e) {
			e.printStackTrace();		}
		
		return tutorial;
		
	}

	
	
     public static List<VideoTutorial> getSampleVideoDetails(int tuteid){
		
		ArrayList<VideoTutorial> suggest = new ArrayList<>();
		
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "SELECT * FROM video_tutorials WHERE Id != '" + tuteid + "' AND type='enable' LIMIT 3;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String videoId = rs.getString(3);
				int views = rs.getInt(4);
				Timestamp uploadDate = rs.getTimestamp(5);
				String category = rs.getString(6);
				String type = rs.getString(7);
				
				VideoTutorial vtutorial = new VideoTutorial(id, title, videoId,category, views, uploadDate,type);
				suggest.add(vtutorial);
			
					
			}
		}catch(Exception e) {
				e.printStackTrace();		}
			
			return suggest;
      
     }
     
     
     public static List<String>getTopRankedVideos(){
    	 ArrayList<String> ranked = new ArrayList<>();
    	 try {
    		 con = DBConnect.getConnection();
 			 stmt = con.createStatement();
    		 String sql = "SELECT Title FROM video_tutorials where type='enable' ORDER BY Views DESC LIMIT 5";
    		 ResultSet rs = stmt.executeQuery(sql);
    		 
    		 while(rs.next()) {
    			 String title = rs.getString(1);
    			 
    			 ranked.add(title);
    		 }
    		 
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }
    	 
    	 return ranked;
     
     }
     
     
     public static HashMap<String , Integer >videosOfCategory(String pcategory){
    	 HashMap<String, Integer> map = new HashMap<>();
    	 try {
    		 con = DBConnect.getConnection();
 			 stmt = con.createStatement();
    		 String sql = "SELECT ID,Title FROM video_tutorials where category = '"+pcategory+"'";
    		 ResultSet rs = stmt.executeQuery(sql);
    		 
    		 while(rs.next()) {
    			 int id = rs.getInt(1);
    			 String title = rs.getString(2);
    			 
    			 map.put(title,id);
    		 }
    		 
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }
    	 
    	 return map;
     
     }
     
     
     
     public static List<VideoTutorial> getvideoDetailFromId(int pid){
 		
 		ArrayList<VideoTutorial> suggest = new ArrayList<>();
 		
 		try {
 			con = DBConnect.getConnection();
 			stmt = con.createStatement();
 			
 			String sql = "SELECT * FROM video_tutorials WHERE Id = '"+pid+"' ";
 			ResultSet rs = stmt.executeQuery(sql);
 			
 			while(rs.next()) {
 				int id = rs.getInt(1);
 				String title = rs.getString(2);
 				String videoId = rs.getString(3);
 				int views = rs.getInt(4);
 				Timestamp uploadDate = rs.getTimestamp(5);
 				String category = rs.getString(6);
 				String type = rs.getString(7);
 				
 				VideoTutorial vtutorial = new VideoTutorial(id, title, videoId,category, views, uploadDate , type);
 				suggest.add(vtutorial);
 			
 					
 			}
 		}catch(Exception e) {
 				e.printStackTrace();		}
 			
 			return suggest;
       
      }
     
     
     
     public static boolean updateVideo(int id , String title , String videoId , String category , String type) {
 		
 		try {
           
 			con = DBConnect.getConnection();
 			stmt = con.createStatement();
 			
 			String sql = "UPDATE video_tutorials SET Title = '" + title + "', Category = '" + category + "' , YoutubeUrl = '"+videoId+"',Type = '"+type+"' WHERE Id = '"+id+"'";

 			int result = stmt.executeUpdate(sql);    // result can be 0 or 1
 		    
 		    if(result > 0) {
 		    	isSuccess = true;
 		    }
 		    else {
 		    	isSuccess = false;
 		    }	
 			
 		}
 		catch(Exception e) {
 			e.printStackTrace();
 		}
 		
 		
 		return isSuccess;
 	}
      
     
     public static boolean deleteVideo(int id) {
  		
  		try {
            
  			con = DBConnect.getConnection();
  			stmt = con.createStatement();
  			
  			String sql = "Delete from video_tutorials WHERE Id = '"+id+"'";

  			int result = stmt.executeUpdate(sql);    // result can be 0 or 1
  		    
  		    if(result > 0) {
  		    	isSuccess = true;
  		    }
  		    else {
  		    	isSuccess = false;
  		    }	
  			
  		}
  		catch(Exception e) {
  			e.printStackTrace();
  		}
  		
  		
  		return isSuccess;
  	}
     
     
     
     public static List<VideoTutorial> getvideosFromCategory(String pcategory){
  		
 		ArrayList<VideoTutorial> suggest = new ArrayList<>();
 		
 		try {
 			con = DBConnect.getConnection();
 			stmt = con.createStatement();
 			
 			String sql;
 			if(pcategory == "Home") {
 				sql = "select * from video_tutorials where type ='enable' ";
 			}
 			else {
 			
 	    	    sql = "SELECT * from video_tutorials where Category = '"+pcategory+"' and type ='enable' ";
 			}
 			
 			
			ResultSet rs = stmt.executeQuery(sql);
 			
 			while(rs.next()) {
 				int id = rs.getInt(1);
 				String title = rs.getString(2);
 				String videoId = rs.getString(3);
 				int views = rs.getInt(4);
 				Timestamp uploadDate = rs.getTimestamp(5);
 				String category = rs.getString(6);
 				String type = rs.getString(7);
 				
 				VideoTutorial vtutorial = new VideoTutorial(id, title, videoId,category, views, uploadDate , type);
 				suggest.add(vtutorial);
 			
 					
 			}
 		}catch(Exception e) {
 				e.printStackTrace();		}
 			
 			return suggest;
       
      }
}

















