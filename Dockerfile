# Use the official Maven base image with OpenJDK 17
FROM maven:3.8.4-openjdk-17

# Set the working directory in the Docker image
WORKDIR /app

# Copy the pom.xml and source code
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Package the application
RUN mvn package

# Move the built jar to the root of /app for easy access
RUN mv target/*.jar ./app.jar

# Run the jar file
CMD ["java", "-jar", "app.jar"]

