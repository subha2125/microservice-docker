# microservice-docker
microservice-docker project

Steps to run this app..

1.Build the jar file by mvn clean install -DskipTests
As of now docker image build is not done by maven docker build. If it is needed pls uncomment <goal>build</goal> under respective project 
pom.xml
 No seperate maven command required to build image. mvn clean install will build code and docker image

2. After successful build type docker-compose up --build and hit enter
3. Docker Automated Build on 08/16/2020
4. Commiting employee-salary-service target for automated docker build

