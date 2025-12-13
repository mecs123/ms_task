FROM eclipse-temurin:21-jdk-alpine
ARG JAR_FILE=build/libs/repo_task-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} repo_task.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "repo_task.jar"]