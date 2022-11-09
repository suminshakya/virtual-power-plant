As per the assigment ,I have created a project on virtual power plant system for aggregating distributed power
and the description for project set up and endpoint are given below


Tools Used
---------------------------------------------------
-Spring Boot framework
-Spring JPA framework
-Junit
-Mokito framework
-MySQL

Setup
--------------------------------------------------
-Download the project from the link https://github.com/suminshakya/VPS

-Create a database named vpps or you can overwrite the name  in application.properties file

-Then open the project in IDE and hit gradlew clean build

-After project build successfully start the project by running main file that is VppsApplication

API
--------------------------------------------------

Run the application and hit the following url in postman

-API to presist in database
The api for saving the batteries is http://localhost:8080/api/v1/batteries

POST Data

Request:
    [
      {
        "name": "Mount Adams",
        "postCode": 6525,
        "capacity": 12000
      },
      {
        "name": "Koolan Island",
        "postCode": 6733,
        "capacity": 10000
      },
      {
        "name": "Cannington",
        "postCode": 6107,
        "capacity": 13500
      },
       {
        "name": "Armadale",
        "postCode": 6992,
        "capacity": 25000
      },
       {
        "name": "Kalamunda",
        "postCode": 6040,
        "capacity": 13500
      }
    ]


-API to get the summary and statistics of batteries
The api for getting summary of the batteries is http://localhost:8080/api/v1/batteries?from=param1&to=param2

where param1 and param2 are range data of post code

Example

Request
http://localhost:8080/api/v1/batteries?from=6107&to=6525

Response

{
    "totalBatteries": 2,
    "totalCapacity": 25500,
    "avgCapacity": 12750.0,
    "batteries": [
        {
            "name": "Cannington",
            "postCode": 6107,
            "capacity": 13500
        },
        {
            "name": "Mount Adams",
            "postCode": 6525,
            "capacity": 12000
        }
    ]
}