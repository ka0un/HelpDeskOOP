# Use Tomcat 9 with JDK 8 as the base image
FROM tomcat:9-jdk16-openjdk

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the entire application directory to Tomcat's webapps directory
COPY build/ /usr/local/tomcat/webapps/ROOT/

# Set the CATALINA_OPTS environment variable to add the classes directory to the classpath
ENV CATALINA_OPTS="-cp /usr/local/tomcat/webapps/ROOT/WEB-INF/classes"

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]