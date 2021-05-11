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

## Change log

Key changes/commits made to the original `master` branch are listed below:

This can also be viewed at:
 * [development branch commits](https://github.com/dazoido/cake-manager/commits/development)
  * [master branch commits](https://github.com/dazoido/cake-manager/commits/master)
```
* 9617f76 Added README.md, removed README.txt
* c8692df refactored controllers, moved download facility to UI controller, update UI nav links
* dab81e9 (origin/development) Merge branch 'development' of https://github.com/dazoido/cake-manager into development
* d22e39f (master-backup) corrected assertion on controller test
* a1dff44 refactored test, added unit tests for CakeDataController
* f512231 added link to download JSOn in UI nav, updated server shudown to graceful
* 0cc79a8 improved response and exception handling for /cakes/add POST
* a356015 refactor DataControllor to RestController, added json file download endpoint
* a79ce24 added validation to prevent cakes with duplicate titles via UI
* d912ca0 implemented form validation for addCakeForm, bit more styling :-)
* af28538 added impl to save cake details submitted via UI form
* 9ce8eb0 updated to load json data on startup added reset data endpoint
* 1cacf06 more template styling to look a bit nicer
* 94b6f29 fix deduping on load data, updated template view for cake list display
* 6f94841 port JerseyService to Controller, as can't use both together doh!
* 96d0b72 add support for thymeleaf templates, add UI controller for user management bits
* 3ba0f73 remove ability to add duplicate cakes with same title
* 4dd8eba remove redundant code, components and config
* edbfca8 added implementation to load json data from url
* 9dc43ed added utility script to refresh vscode classpath
* c3e3d20 updated to use JpaRepository, added test to validate data save and fetch from db
* a188fae added simple utility script for compiling and launching app
* eb2e9d5 added support for spring jpa to use for adding dummy cake
* 62e5b9f fixed package hierachy added skeleton and support for Sprint Boot project
```