FROM java:8
ADD target/employee-info-service.jar employee-info-service.jar
EXPOSE 8184
COPY . /opt/src
ENTRYPOINT ["java","-jar","employee-info-service.jar"]