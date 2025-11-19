# **User Management Service**

## **Base URL**
<a href="https://user-management-service-wnpt.onrender.com">https://user-management-service-wnpt.onrender.com</a>

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


