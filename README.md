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

# added Cloud Gateway
# DB Password --  test
http://localhost:8989/actuator/hystrix.stream  --- API gateway hystrx
http://localhost:8090/hystrix -- open it and paste above url (http://localhost:8989/actuator/hystrix.stream)  to monitor stream

#ELK
#https://www.youtube.com/watch?v=9g-h1biMn2E

http://localhost:9200/_cat/indices -- See ELastic Serach Indices

# Create custom index in Kibana
1. Go to http://localhost:5601/app/dev_tools#/console
2. Fire the below req to create index with name emp-service
PUT /emp-service
{
  "settings": {
    "index": {
      "number_of_shards": 3,  
      "number_of_replicas": 2 
    }
  }
}

3. Create default document
POST /emp-service/default
{
  "name": "event process",
  "instructor":{
    "firstname":"emp",
	"lastName":"service"
  }
}
 O/P
 
 #! Elasticsearch built-in security features are not enabled. Without authentication, your cluster could be accessible to anyone. See https://www.elastic.co/guide/en/elasticsearch/reference/7.13/security-minimal-setup.html to enable security.
#! [types removal] Specifying types in document index requests is deprecated, use the typeless endpoints instead (/{index}/_doc/{id}, /{index}/_doc, or /{index}/_create/{id}).
{
  "_index" : "emp-service",
  "_type" : "default",
  "_id" : "oExYunoB1QyiRb2TJvh-",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 3,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

4. Add logstash.conf file


