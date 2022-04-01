#Spring Boot Customer Rewards Application.

Requirements
For building and running the application you need:

JDK 1.8 or above
Maven 3 or above

Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.customer.rewards.CustomerRewardsApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn clean install
mvn spring-boot:run

Sample data stored in src/main/resources/data.sql

##Rest URLs

###To fetch all customers - Get request http://localhost:8081/customers

###To fetch particular customer - Get request http://localhost:8081/customers/{id}

###To fetch particular customer -Get request http://localhost:8081/createCustomers post body {"name":"maheshTest"}

##h2 console information

##URL - http://localhost:8081/h2-console
##DBURL - jdbc:h2:mem:rewardsdb


