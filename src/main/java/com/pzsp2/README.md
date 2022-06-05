# PZSP2-Backend
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This is backend of Web Application for academic project PZSP2 of the Warsaw University of Technology. It contains connections with the WUT database and 
realize queries on it. The project in made to allow Teachers make ABCD or open question tests, save it, export it to CSV file and send it via link to 
Students which solve the questions and send answers to teacher.
	
## Technologies
Backend part of project is realized with:
* Spring Boot version: 2.6.6
* Maven version: 3.6.3
* Junit: 999
* Lombok
* AssertJ:
* 
	
## Setup
To run this project, use these commands using mvn on root folder:
```
$ mvn spring-boot:run
```

To only compile application sources:
```
$ mvn compiler:compile
```

To create jar file:
```
$ mvn jar:jar
```

Backend is accessed by browser by http://localhost:8080/

To run full application checkout our frontend: https://github.com/JakubTomaszewski/PZSP2-Frontend
