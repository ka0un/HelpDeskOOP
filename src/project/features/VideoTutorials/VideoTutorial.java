package project.features.VideoTutorials;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class VideoTutorial {
    private int id;
    private String title;
    private String videoID;
    private String category;
    private int views;
    private Timestamp uploadDate;
    private String type;

    public VideoTutorial(int id, String title, String videoID, String category, int views, Timestamp uploadDate, String type) {
        this.id = id;
        this.title = title;
        this.videoID = videoID;
        this.category = category;
        this.views = views;
        this.uploadDate = uploadDate;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoID() {
        return videoID;
    }

    public int getViews() {
        return views;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public String getCategory() {
        return category;
    }

    public String getTimeSinceUpload() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime uploadLocalDateTime = uploadDate.toLocalDateTime();

        Duration duration = Duration.between(uploadLocalDateTime, currentTime);
        
        // Extract days, hours, and minutes from the duration
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        // Debug output
        System.out.println("Difference: " + days + " days, " + hours + " hours, " + minutes + " minutes.");

        // Return string indicating time since upload
        if (days > 0) {
            return days + " day" + (days > 1 ? "s" : "") + " ago"; // pluralization
        } else if (hours > 0) {
            return hours + " hour" + (hours > 1 ? "s" : "") + " ago"; // pluralization
        } else {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago"; // pluralization
        }
    }

    // Change method to non-static to access instance variable
    public boolean isEnabled() {
        return "enable".equals(type); // Use .equals() to compare strings
    }
    
    
}
