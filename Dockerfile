# Use the official Tomcat 8.5 base image
FROM tomcat:8.5-alpine

# Set the working directory in the container
WORKDIR /usr/local/tomcat/webapps/

# Copy the application WAR file into the container
COPY dist/ange_25489.war /usr/local/tomcat/webapps/

# Expose the ports your application runs on
EXPOSE 8080
EXPOSE 8443

# Set the entry point for the container to run Tomcat
ENTRYPOINT ["catalina.sh", "run"]
