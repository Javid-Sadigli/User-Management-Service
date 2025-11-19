# **User Management Service**
The User Management Service is a backend application that provides a reliable and structured way to manage user information. It offers full CRUD functionality through a RESTful API and is designed with maintainability, clarity, and scalability in mind.
The system follows a straightforward architecture where user-related operations are handled cleanly through layered components such as controllers, services, and repositories. In this project, database structure changes are versioned, tests ensure the correctness of business rules, and the application is prepared for easy deployment using containerization.

## **Table of Contents** 
* [Features](#features)
* [Technical Specifications](#technical-specifications)
* [Getting Started](#getting-started)
* [Deployment](#deployment)
* [API Endpoints](#api-endpoints)
* [Contributing](#contributing)

## **Features** 
- Create, retrieve, update, and delete users
- RESTful API following common industry standards
- Pagination support for listing users
- Clean architecture with separation of concerns
- Comprehensive business-logic unit tests
- Easy deployment with containerization

## **Technical Specifications**
- Spring Boot 3
- PostgreSQL
- Liquibase for database migrations
- Docker & containerized application setup
- RESTFul API design
- Pagination
- Unit Testing (JUnit, Mockito)
- Deployment on Render
- CI/CD pipelines 

## **Getting Started** 
For getting started with the application, you need to go through the following steps:
#### Step 1: Clone the repository
Firstly, you need to clone the repository, to get application code. Type these commands : 
```sh
git clone https://github.com/Javid-Sadigli/User-Management-Service.git
cd User-Management-Service
```

#### Step 2: Run the application 
There is a `docker-compose.yaml` file which allows you to run project easily, so you can just type these command: 
```sh
docker-compose up -d
```

#### Step 3: Open the application through browser
Since you have runned the application, you can go to `localhost:8080` to see it. 

Additionally, you can go to `localhost:8080/docs` to see the Swagger documentation of it. 


## **Deployment** 
The project is deployed on Render, using a Docker-based deployment workflow.

<a href="https://user-management-service-wnpt.onrender.com/">If you want to see the deployed application, please click here.</a>

All environment variables required for the database connection are configurable through the Render dashboard.

## **API Endpoints**

### Check if server is running
`GET /test-server`

**Responses:**
- **200 OK** - Server is running
  `"string"`


## Get All Users
`GET /api/v1/user`

**Query Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| pageNumber | integer | Yes | Page number for pagination |
| pageSize | integer | Yes | Number of items per page |

**Responses:**
- **200 OK** - Successfully retrieved users
  ```json
    {
        "success": true,
        "data": [
            {
                "id": 1,
                "userRole": "EMPLOYEE",
                "fullName": "John Doe",
                "email": "john.doe@example.com",
                "phoneNumber": "+1234567890"
            }
        ],
        "status": 200,
        "message": "Success"
    }
  ```

## Create User
`POST /api/v1/user`

**Request Body:**
```json
{
    "userRole": "EMPLOYEE",
    "fullName": "John Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "+1234567890"
}
```

**User Role Options:**
- `EMPLOYEE`
- `MANAGER`
- `HR`
- `CEO`
- `CTO`

**Responses:**
- **201 Created** - User successfully created
  ```json
    {
        "success": true,
        "data": null,
        "status": 201,
        "message": "User created successfully"
    }
  ```

## Get User by ID
`GET /api/v1/user/{id}`

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | integer | Yes | User ID |

**Responses:**
- **200 OK** - Successfully retrieved user
    ```json
    {
        "success": true,
        "data": {
            "id": 1,
            "userRole": "EMPLOYEE",
            "fullName": "John Doe",
            "email": "john.doe@example.com",
            "phoneNumber": "+1234567890"
        },
        "status": 200,
        "message": "Success"
    }
    ```
- **404 Not Found**
    ```json
    {
        "success": false,
        "data": null,
        "status": 404,
        "message": "User was not found with id: 314"
    }
    ```

## Update User
`PUT /api/v1/user/{id}`

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | integer | Yes | User ID |

**Request Body:**
```json
{
  "userRole": "MANAGER",
  "fullName": "John Smith",
  "email": "john.smith@example.com",
  "phoneNumber": "+1234567890"
}
```

**User Role Options:**
- `EMPLOYEE`
- `MANAGER`
- `HR`
- `CEO`
- `CTO`

**Responses:**
- **200 OK** - User successfully updated
  ```json
    {
        "success": true,
        "data": {
            "id": 1,
            "userRole": "MANAGER",
            "fullName": "John Smith",
            "email": "john.smith@example.com",
            "phoneNumber": "+1234567890"
        },
        "status": 200,
        "message": "User updated successfully"
    }
  ```
- **404 Not Found**
    ```json
    {
        "success": false,
        "data": null,
        "status": 404,
        "message": "User was not found with id: 314"
    }
    ```

## Delete User
`DELETE /api/v1/user/{id}`

**Path Parameters:**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | integer | Yes | User ID |

**Responses:**
- **200 OK** - User successfully deleted
  ```json
    {
        "success": true,
        "data": null,
        "status": 200,
        "message": "User deleted successfully"
    }
  ```
- **404 Not Found**
    ```json
    {
        "success": false,
        "data": null,
        "status": 404,
        "message": "User was not found with id: 314"
    }
    ```

## **Contributing**
Contributions are welcome! Follow these steps to contribute:
* Fork the project.
* Create a new branch: `git checkout -b feature/your-feature`.
* Make your changes.
* Submit a pull request.