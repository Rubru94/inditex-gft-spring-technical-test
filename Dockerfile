################################################################################################
# JAR always up to date and the whole build and run process is encapsulated in the Dockerfile. #
################################################################################################

### STAGE 1 --> An official Maven image is used to compile the JAR.

# Step 1: Use an official Maven image to build the JAR
FROM maven:3.9.9-amazoncorretto-21-debian AS build

# Step 2: Set the working directory inside the build container
WORKDIR /app

# Step 3: Copy the pom.xml and source code into the build container
COPY pom.xml /app/
COPY src /app/src

# Step 4: Build the JAR file. Flag -X enables the detailed mode.
RUN mvn clean package -DskipTests -X

### STAGE 2 --> An Amazon Corretto base image is used for the application runtime, copying the generated JAR from the build stage.

# Step 1: Use an official amazoncorretto base image from Docker Hub
FROM amazoncorretto:21

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Set the JAR file path
ARG JAR_FILE=target/*.jar

# Step 4: Copy the Spring Boot JAR file from the build stage
COPY --from=build /app/${JAR_FILE} /app/inditex-technical-test-app.jar

# Step 5: Copy application.yml into the container
COPY src/main/resources/application.yaml /app/application.yaml

# Step 6: Expose the port your application runs on
EXPOSE 8080

# Step 7: Define the command to run your Spring Boot application
CMD ["java", "-Dspring.config.location=/app/application.yaml", "-jar", "/app/inditex-technical-test-app.jar"]