FROM java:8
ADD target/hystrix.jar hystrix.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","hystrix.jar"]