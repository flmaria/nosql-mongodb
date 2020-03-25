# study-nosql-mongodb
Tests using no-sql database with CRUD operations


## What does the project do?

Searches for 5-year historical prices of 4 stock exchange companies and includes these data in a standard database and no-sql database.

## Goal

Evaluate the massive performance of data in a no-sql database learn its CRUD operations.

# Settings for execution

After copying the project to your computer:

* Install the MongoDB database on your computer and start its service;
* Install the PostgreSQL database on your computer and start its service;
* Change the settings for accessing the PostgreSQL database in the "/study-nosql-mongodb/src/main/resources/database.properties" file;

# Technology used

* YahooFinanceAPI - Library to import stock data
* Spring Framework
  * Core - All services were created using dependency injection;
  * JDBC - Access to the PostgreSQL database
* Postgresql - To store data in a standard database
* MongoDB - To store data in a No-SQL Database

# Expected result

Run the "/study-nosql-mongodb/src/main/java/com/flm/Main.java" file.

* Program should automatically create the tables used in PostgreSQL;
* The program must insert historical stock price data individually into the PostgreSQL database and present the total transaction time;
* Program must insert historical stock price data in batch in the PostgreSQL database and present the total time of the operation;
* The program must insert historical stock price data individually into the MongoDB database (No-SQL) and present the total time of the transaction;
* The program must insert historical stock price data in batch in the MongoDB database (No-SQL) and present the total time of the operation;
