1 - download project.
2 - import project as maven project
3 - run as spring application with Java 8 

*** This project is spring boot application which contains spring-boot-starter-data-jpa for database crud operations.  
spring-boot-starter-web to retrive data from service providers. H2 memory database to store data and retrive data.  
Hibernate is being used because of object relation mapping. GSON is used to parse json data.  

*** schema.sql and data.sql is providing template data for Conversion List API by using H2 database.

*** application.properties file has our database connection info and also has service providers url. 

*** Database can be shown by the following url after application started.
http://localhost:8080/h2/ then press connect button to see database which contains EXCHANGE_VALUE table.

*** APIS WHICH ARE BELOW MAY HAVE SOME DEFAULT VALUES PLEASE CHECK CODE BEFORE SENDING EMPTY REQUEST PARAMETERS.

*** Exchange Rate API examples url:
    http://localhost:8080/getExchangeRate?fromPair=IDR&toPair=EUR
    http://localhost:8080/getExchangeRate?fromPair=EUR&toPair=USD

*** conversion API example url:
    http://localhost:8080/retrieveExchangeValue?amount=2000&fromPair=EUR&toPair=USD
    http://localhost:8080/retrieveExchangeValue?amount=30&fromPair=IDR&toPair=USD
    http://localhost:8080/retrieveExchangeValue?amount=50000&fromPair=EUR&toPair=USD

*** Conversion List API example url:
    http://localhost:8080/getListOfConversion?transactionId=1000&transactionDate=2010-10-21&pageSize=2
    http://localhost:8080/getListOfConversion?transactionId=123&transactionDate=2010-09-21&pageSize=2
    
*** JUnit tests were provided feel free to change variables and test the application.

*** central error logging mechanism file financialApp.log. Path will be created after running application 