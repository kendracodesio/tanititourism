#Backend Dockerfile

#Use OpenJDK image as the base image
FROM --platform=linux/amd64 openjdk:17

#Set the working directory
WORKDIR /app

#Copy the Spring Boot JAR file to the working directory
COPY target/tanititourism-0.0.1-SNAPSHOT.jar /app/springboot-app.jar

#Set environment variable for spring profile
ENV SPRING_PROFILE_ACTIVE=prod


#Expose the port that Spring Boot is running on
EXPOSE 8080

#Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "springboot-app.jar"]