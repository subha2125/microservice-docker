FROM java:8
ADD target/cloud-api-gateway.jar cloud-api-gateway.jar
EXPOSE 8989
ENTRYPOINT ["java","-jar","cloud-api-gateway.jar"]