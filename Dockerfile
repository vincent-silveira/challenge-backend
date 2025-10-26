# Step 1: Use a Maven image to build the app
FROM maven:3.9.0-eclipse-temurin-19 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download the dependencies first (to leverage Docker cache)
COPY pom.xml .

# Download dependencies (this will cache dependencies layer if unchanged)
RUN mvn dependency:go-offline

# Now copy the rest of the project
COPY src ./src

# Build the app (this creates the target/challenge-backend-0.0.1-SNAPSHOT.jar)
RUN mvn clean package -DskipTests -Dmaven.test.skip=true


# Step 2: Now create a minimal runtime image
FROM eclipse-temurin:17-jdk-focal

# Set the working directory in the runtime image
WORKDIR /app

# Copy the built .jar file from the build stage
COPY --from=build /app/target/challenge-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
