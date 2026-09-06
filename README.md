# one-hub-service
OneHub Engineering Technical Task - Benifex

## Employee API
Restful API for HR Administrators to add new starters so that they can gain access to OneHub and
take full advantage of their employment package.

```http
  POST /employees
```

| Parameter      | Type   | Description       |
|:---------------|:-------|:------------------|
| `Request Body` | `JSON` | Request body JSON | 

### Request Body
```http
  {
    "title": "Senior Backend Engineer",
    "firstName": "John",
    "surName": "Doe",
    "dateOfBirth": "1998-07-16",
    "gender": "Male",
    "email": "johndoe@gmail.com",
    "address": "Talisay City, Cebu"
  }
```

### Request Fields

| Field         | Type     | Required | Description                          |
|:--------------|:---------|:---------|:-------------------------------------|
| `title`       | `String` | Yes      | Employee title                       |
| `firstName`   | `String` | Yes      | Employee first name                  |
| `surName`     | `String` | Yes      | Employee surname                     |
| `dateOfBirth` | `String` | Yes      | Date of birth in `yyyy-MM-dd` format |
| `gender`      | `String` | Yes      | Employee gender                      |
| `email`       | `String` | Yes      | Valid and unique email address       |
| `address`     | `String` | Yes      | Employee address                     |


### Successful Response - HTTP 200 OK
```http
  {
    "data": {
        "address": "Talisay City, Cebu",
        "dateOfBirth": "1998-07-16",
        "email": "johndoe@gmail.com",
        "employeeNo": "b9b428c5-8681-4b98-ae8f-804acb590f00",
        "firstName": "John",
        "gender": "Male",
        "id": 3,
        "surName": "Doe",
        "title": "Senior Backend Engineer"
    },
    "message": "Successful",
    "statusCode": 200,
    "success": true
  }
```

### Validation Error Response - HTTP 400 Bad Request
If one or more fields fail validation, the API returns:
```http
  {
    "status": "BAD_REQUEST",
    "statusCode": 400,
    "timestamp": "2026-09-06T10:30:00",
    "message": "There are validation errors in the request.",
    "errorDetails": [
        {
            "field": "firstName",
            "message": "The field must not be null."
        },
        {
            "field": "dateOfBirth",
            "message: "Invalid date or invalid date format. Should use YYYY-MM-DD"
        }
    ]
  }
```

## What I'd do next if I have more time
- Create separate API for filtering employee by their `EmployeeNo`,`email`
- Create table/entity named `EmploymentPackage` and `Benefits`
- Then the DB design would be `Employee` -> manyToOne -> `EmploymentPackage` -> oneToMany -> `Benefits`
- Implement role-based access control to ensure that only employees with HR Administrator role can add new starters. Regular employees are restricted from performing this action.

### Note: I'm able to run this application locally.