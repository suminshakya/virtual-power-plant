
# Virtual Power Plant System

Virtual power plant system for aggregating distributed power sources into
a single cloud based energy provider.



## Tech Stack

**Language:** Core Java

**Framework:** Spring Boot, Spring Data JPA, Hibernate

**Database:** MySQL

**API Testing Application:** Postman

## Run Locally

Clone the project

```bash
  git clone https://github.com/suminshakya/virtual-power-plant
```

Create a database named vpps in your MySQL database

```bash
  CREATE DATABASE vpps;
```

Go to the project directory

```bash
  cd virtual-power-plant
```

Install dependencies

```bash
  gradlew clean build
```

Start the server with proper username and paswword of MySQL database

```bash
  gradlew bootRun --args="--spring.datasource.username=<DATABASE_USERNAME> --spring.datasource.password=<DATABASE_PASSWORD>"
```


## API Reference

### Create batteries

```http
  POST /api/v1/batteries
```

| Variable | Type     | Required                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Yes** |
| `postCode` | `int` | **Yes**|
| `capacity` | `int` | **Yes**|

#### Request
```json
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

  ```
#### Response
  ```json
   {
    "message": "Batteries has been successfully created!!!",
    "status": "CREATED"
  }
 ```

## Get Summary and Statistics of batteries

```http
  GET /api/v1/batteries?from=start-value-postcode&to=end-value-postcode
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `from`      | `int` | **Required**.  Start value of post code|
| `to`      | `int` | **Required**. End value of post code |

#### Request

```http
  /api/v1/batteries?from=6107&to=6525
```

#### Response
```json
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
 ```


