# MedKit Plus BeWell Sanitas Api
Back-end for MedKit Plus BeWell Sanitas Aplication. 

Project is our engineering thesis. Depends on enabling quick transfer of medical test results to the patient.

# Technologies:
* Java 11
* Spring-Boot 2
* Hibernate
* JUnit
* Mockito
* PostgreSQL 12
* Lombok

# Instalation

To run this project you need to install locally:

* jdk-11
* postgresql
* Lombok (plugin IntelliJ)

# PostgreSQL config

Before run project, you need to set up the postgreSQL configuration correctly. Create:

###### username = postgres
###### password = root
###### port = 5432

Later you need to create database with name: 
###### engineering_thesis

# Run application

In order to run application you need to:

* Build application:

###### mvn clean install

* Run application:

###### mvn spring-boot:run

* You can run application from run dir via:

###### mvn_run_dbg.bat (create db from scratch and fill data via data.sql file)
###### mvn_run_prod.bat (doesn't perform any changes to db itself)

# Spring Security - create token

To login, use sample login data or register a new account.

In order to login use endpoint /login and post data

    "email" : ,
    "password" :
    
It'll give you back a token. Use token to pass secured endpoints.