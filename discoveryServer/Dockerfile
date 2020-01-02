FROM java:8
ADD target/discoveryserver.jar discoveryserver.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","discoveryserver.jar"]