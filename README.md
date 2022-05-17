# PZSP2-Backend
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is web-application made for academic staff. It alows to conduct tests by creating questions, tests and sharing them to students via a link.
This repository represents backbone of the project - backend which acts as a layer connecting frontend with database.
	
## Technologies
Project is created with:
* Spring Boot version: 2.6.6
* Maven version: 3.6.3
* Junit: 999
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

To compile, package, convert application to a Docker image and run this image:


```
$ mvn spring-boot:build-image
$ docker run -it -p8080:8080 {name_of_the_image} 
```


