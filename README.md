# Cake Manager Micro Service



The contents of this github repository represents my solution to a fictitious
micro-service assignment designed by Waracle.

The original assignment can be viewed at https://github.com/Waracle/cake-manager

----

## Overview

My solution to this assignment is implemented as a Spring Boot application. 

Whilst the initial issues with the intern's code were fixable (i.e. source files for Java code were not packaged correctly etc) I decided that it was a more efficient use of time to re-implement the solution in a more modern framework (as suggested). Spring Boot was selected as it supports standalone rapid application development which can easily be tranistioned to a production-ready service.

### Getting Started

1. Download or clone this repository to an empty folder (i.e. `waracle-cakemanager`) on your local machine:

   `git clone https://github.com/dazoido/cake-manager.git` waracle-cakemanager

1. To launch the application, navigate to the `waracle-cakemanager` folder and run the following command:

   `./mvnw spring-boot:run`

   Alternatively, if you have Maven installed globally in your environment, run the following command:

   `mvn spring-boot:run`

1. When the application is launched it is prepopulated using the following source data:
   
   https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json

## Service usage

The service is accessible via two primary interfaces, a Web User Interface  and REST interface.

### Web UI

This interface allows a user to view all cakes stored and add new cakes. Users are also able to download all cakes stored as a JSON file via the interface. The interface does not currently require authentication to be accessed.

Its is available at http://localhost:8080

### REST interface

The REST interface provides the following endpoints:

#### Fetch Cakes

Returns a list of cakes as JSON

**GET http://localhost:8080/cakes**

**`Example request:`**

```
curl --location --request GET 'localhost:8080/cakes'
```

#### Add Cake

Adds a cake to the data store

**POST http://localhost:8080/cakes**

**`Example request:`**

```
curl --location --request POST 'localhost:8080/cakes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title":"Chocolate Cake",
    "desc":"A super chocolatey cake",
    "image":"https://food-images.files.bbci.co.uk/food/recipes/easy_chocolate_cake_31070_16x9.jpg"
}'
```

#### Reset Cake Data 

Drops cake data and reloads from [source](https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json)

**POST http://localhost:8080/cakes/reset** 

**`Example request:`**
```
curl --location --request POST 'localhost:8080/cakes/resetData'
```

