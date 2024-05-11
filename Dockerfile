# Use an official Java runtime as a parent image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the .jar file from your target directory to the container
COPY ./target/SunriseSunsetBackend-0.0.1-SNAPSHOT.jar /app/sunriseSunsetBackend.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the app when the container launches
CMD ["java", "-jar", "sunriseSunsetBackend.jar"]
