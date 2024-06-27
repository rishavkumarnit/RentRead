# RentRead



## Introduction

This is a RESTful API service using Spring Boot to manage an online book rental system. It uses MySQL to persist the data.



## Features

- CRUD operations in RDBMS table
- Book Management
- Rental Management
- Authentication and Authorization
- Basic testing through Mockito and MVC


## Installation



### 1. Add repository
```
git clone [repository]
```


### 2. Add dependencies
```
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.mysql:mysql-connector-j'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
```

### 3. Configuration 
```
spring.application.name=RentRead
spring.datasource.url=jdbc:mysql://localhost:3306/rentread
spring.datasource.username=<userid>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update
```


### 4. Run softwares
```
- Java 21
- MySQL
- Gradle
``` 


### 5. Execute
```
./gradlew test
./gradlew build
./gradlew bootrun
```


**The server will start and be accessible at http://localhost:8080**


To know more Navigate : [Postman API Documentation](https://www.postman.com/technical-cosmonaut-13105159/workspace/my-workspace/collection/36174974-35ee104e-e0df-4fe5-93e8-20ec13e70fa7?action=share&creator=36174974)
___