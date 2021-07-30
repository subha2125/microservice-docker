#FROM java:8
#ADD target/employee-salary-service.jar employee-salary-service.jar
#EXPOSE 8183
#ENTRYPOINT ["java","-jar","employee-salary-service.jar"]

# Added MultiStage Docker build

#FROM docker.artifactory.rosetta.ericssondevops.com/maven:3.5.4-jdk-8
FROM maven:3.6.1-jdk-8 as builder
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN mvn clean package


FROM java:8
COPY --from=builder /app/source/target/employee-salary-service.jar /app/employee-salary-service.jar
EXPOSE 8183
ENTRYPOINT ["java","-jar", "/app/employee-salary-service.jar"]