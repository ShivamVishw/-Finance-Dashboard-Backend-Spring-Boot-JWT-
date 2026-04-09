# 💰 Finance Dashboard Backend (Spring Boot + JWT)

A secure and scalable backend system for managing personal financial data, built using **Spring Boot**, **JWT Authentication**, and **MySQL**.
This project demonstrates real-world backend concepts including authentication, authorization, and financial data processing.

---

## 🚀 Features

### 🔐 Authentication & Security

* User Registration & Login
* JWT-based Authentication
* Role-Based Access Control (RBAC)

  * ADMIN
  * ANALYST
  * VIEWER
* Secure password storage using BCrypt

---

### 🧾 Financial Records Management

* Add financial records (Income / Expense)
* Fetch user-specific records
* Data isolation (each user sees only their data)

---

### 📊 Dashboard Summary

* Total Income calculation
* Total Expense calculation
* Balance computation (Income - Expense)

---

## 🧠 Tech Stack

| Layer      | Technology                  |
| ---------- | --------------------------- |
| Backend    | Spring Boot                 |
| Security   | Spring Security + JWT       |
| Database   | MySQL                       |
| ORM        | Spring Data JPA (Hibernate) |
| Build Tool | Maven                       |
| Language   | Java 17                     |

---

## 🏗️ Project Architecture

```
com.finance.dashboard
│
├── controller      → REST APIs
├── service         → Business Logic
├── repository      → Database Layer
├── entity          → JPA Entities
├── dto             → Data Transfer Objects
├── security        → JWT + Filters
└── config          → Security Configurations
```

---

## 🔐 Authentication Flow

1. User logs in → receives JWT token
2. Token is sent in request headers:

   ```
   Authorization: Bearer <token>
   ```
3. JWT Filter validates token
4. User is authenticated & authorized based on role

---

## 📌 API Endpoints

### 🔑 Auth APIs

#### Register User

```
POST /auth/register
```

#### Login

```
POST /auth/login
```

---

### 🧾 Financial APIs

#### Add Record

```
POST /api/records
```

#### Get All Records (User-specific)

```
GET /api/records
```

#### Get Summary

```
GET /api/records/summary
```

---

## 📥 Sample Request
🔄 Complete API Flow (Step-by-Step)

Follow this sequence to properly test and use the application.

#### 1️⃣ Register User
```
POST /auth/register
```

Request Body:
<br>
```json
{
  "name": "Admin User", 
  "email": "admin@test.com", 
  "password": "1234", 
  "role": "ADMIN" 
}
```

#### 2️⃣ Login (Get JWT Token)
```
POST /auth/login
```

#### ⚠️ Important:

Do NOT send Authorization header in this request
<br>
Request Body:
```json
{
  "email": "admin@test.com", 
  "password": "1234" 
}
```
<br>
Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```
👉 Copy the token from the response

#### 3️⃣ Use Token in Protected APIs

Add the following header in all secured requests:

Authorization: Bearer <your_token>

#### 4️⃣ Add Financial Record
```
POST /api/records
```

Headers:

Authorization: Bearer <your_token>
<br>
Request Body:

```json
{
  "amount": 5000, 
  "recordType": "INCOME", 
  "category": "Salary", 
  "time": "2026-04-08" 
}
```

#### 5️⃣ Get All Records
```
GET /api/records
```

```json
[
    {
        "id": 1,
        "amount": 5000.0,
        "recordType": "INCOME",
        "category": "Salary",
        "time": "2026-04-08",
        "user": {
            "id": 1,
            "name": "Admin User",
            "email": "admin@test.com",
            "role": {
                "id": 1,
                "name": "ADMIN"
            },
            "active": true,
            "createdAt": "2026-04-08T15:21:54.509624",
            "updatedAt": null
        }
    },
    {
        "id": 2,
        "amount": 5000.0,
        "recordType": "INCOME",
        "category": "Salary",
        "time": "2026-04-08",
        "user": {
            "id": 1,
            "name": "Admin User",
            "email": "admin@test.com",
            "role": {
                "id": 1,
                "name": "ADMIN"
            },
            "active": true,
            "createdAt": "2026-04-08T15:21:54.509624",
            "updatedAt": null
        }
    }
]
```

#### 6️⃣ Get Summary
```
GET /api/records/summary
```

```json
{
    "totalIncome": 10000.0,
    "totalExpense": 0.0,
    "balance": 10000.0
}
```

#### ⚠️ Common Errors & Solutions

❌ 403 Forbidden

## Possible reasons:

Missing token
Expired token
Insufficient role permissions
❌ JWT Expired
io.jsonwebtoken.ExpiredJwtException

## Solution:

Login again using /auth/login
Generate a new token
Use the new token in headers
💡 Notes
JWT tokens have a limited validity for security purposes
Always include the token for protected APIs
Each user can access only their own financial data
## 🔐 Roles & Access Control

| Role    | Access           |
| ------- | ---------------- |
| ADMIN   | Full access      |
| ANALYST | Limited access   |
| VIEWER  | Read-only access |

---

## ⚙️ Setup Instructions

### 1️⃣ Clone Repository

```
git clone <your-repo-url>
cd finance-dashboard-backend
```

---

### 2️⃣ Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/finance_dashboard
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 3️⃣ Run Application

```
mvn spring-boot:run
```

---

## 🧪 Testing

Use Postman to test APIs:

* Register → Login → Get Token
* Add Token in Headers
* Test secured APIs

---

## 📸 Screenshots :

<img width="1582" height="958" alt="Screenshot 2026-04-09 at 4 43 07 PM" src="https://github.com/user-attachments/assets/97066354-e64b-43e7-ad83-d3c585dc6147" />
<img width="1582" height="1035" alt="Screenshot 2026-04-09 at 4 46 30 PM" src="https://github.com/user-attachments/assets/c8b34328-aac5-4fef-84e1-a872466f56f1" />
<img width="1582" height="915" alt="Screenshot 2026-04-09 at 4 56 02 PM" src="https://github.com/user-attachments/assets/298609fc-aa50-46a6-902c-bcd9cbde0633" />
<img width="1582" height="1035" alt="Screenshot 2026-04-09 at 4 57 22 PM" src="https://github.com/user-attachments/assets/9a702c7d-0fd3-41ba-90e4-3aa05f65f4ca" />
<img width="1582" height="1035" alt="Screenshot 2026-04-09 at 4 57 32 PM" src="https://github.com/user-attachments/assets/a4a73491-9afe-4e3f-be46-98e2b2e06a94" />

* Postman API responses
* Database tables
* JWT token usage

---

## ⚠️ Security Notes

* Passwords are encrypted using BCrypt
* JWT tokens expire after a defined time
* Sensitive fields like password are hidden from responses

---

## 🚀 Future Improvements

* Update & Delete APIs
* Pagination & Sorting
* Advanced filtering
* Refresh Token mechanism
* Docker deployment
* Frontend integration

---

## 👨‍💻 Author


## Connect: [**Shivam Vishwakarma**](https://www.linkedin.com/in/shivam-vishwakarma-b981b3206/)

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
