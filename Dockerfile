FROM eclipse-temurin:17
ADD build/libs/ResumeBuilder-0.0.1-SNAPSHOT.jar resume-builder-backend.jar
ENTRYPOINT ["java","-jar","/resume-builder-backend.jar"]
EXPOSE 8080