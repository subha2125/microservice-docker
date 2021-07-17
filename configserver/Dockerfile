FROM java:8
ADD target/configserver.jar configserver.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","configserver.jar"]