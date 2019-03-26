# Filtering Matches

This project was made with the following technologies: 

Back-end: Spring Boot, Spring MVC and Spring Data

Front-end: React

Database: Mongo DB

### Requeriments to build and run the application

To deploy and execute this application in your machine you need the following requirements installed:

* Maven
* Docker
* Docker Compose
* JDK8
* Node


### Database set up

Go to the project root directory `$DIRECTORY_PATH/filtering-matches` and execute:

`docker-compose up -d`

This command will create a Mongo DB container and import all data required. 

If everything is fine you will see a new Docker container running on port `27017`.

Use the command `docker ps` to verify. You should see the container `filtering-matches_mongodb_1` with status `UP`.

### Back-end set up

The back-end has unit tests and integration tests.

Go to the project root directory `$DIRECTORY_PATH/filtering-matches/filtering-service`

To run the unit tests execute the command:

`./mvnw clean test`

To run the integration tests you need to have the MongoDB container up and running, after that, execute the command:

`./mvnw clean integration-test`

This command will make many HTTP GET requests to the REST API.

To run the application execute the command: 

`./mvnw spring-boot:run`

If everything was fine you should see a new process running on port `8080`. To verify just access Swagger UI: [link](http://localhost:8080/swagger-ui.html "http://localhost:8080/swagger-ui.html")


Example of HTTP request with all filters:

  `curl -X GET \
  'http://localhost:8080/match/filter?page=0&size=10000&has_photo=true&in_contact=true&favourite=true&compatibility_score=0.82&age=84&height=200&distance_in_km=278&longitude=-0.118092&latitude=51.509865' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: b04e2caf-cbcb-1f78-ef9c-6cc852dfeb72'`

  ### Front-end set up

Go to the project root directory `$DIRECTORY_PATH/filtering-matches/matches-ui`.

Execute install command:

`npm install`

Execute run command:

`npm run`

You should see a new process running on port `3000` [endpoint](http://localhost:3000 "http://localhost:3000"). 

I'm using **Cypress** to run some screen integration tests:

https://www.cypress.io/

To open **Cypress** runner execude the following command as root:

`sudo npm run cypress:open`

The **Cypress** runner application will be opened. Click on **Run all specs** to execute screen tests.









