
#Step to Database setup
1. Create Database PostgreSQL with name "gate_api"
2. Execute every DDL in PostgreSQl
3. Insert to table t_user to create "Admin" account

---
#Step by step running service
1. Unzip file 
2. Load maven project
3. execute "mvn clean install"
4. run application with "mvn spring-boot:run"

### 🔹 Auth
**GET** `/api/authenticate
-- you will get token and using this token for every service access

### 🔹 Create User

**POST** `/create-user`

- **Request Body**: `UserRequestDto`
- **Response**: `UserResponseDto`

### 🔹 Get All Users

**GET** `/get-all-user`

- **Response**: `List<UserResponseDto>`

### 🔹 Get User by ID

**GET** `/get-user?id={UUID}`

- **Query Param**: `UUID id`
- **Response**: `UserResponseDto`

### 🔹 Update User

**PUT** `/update`

- **Request Body**: `UserRequestDto`
- **Response**: `String` confirmation message

### 🔹 Delete User

**POST** `/delete?id={UUID}`

- **Query Param**: `UUID id`
- **Response**: `String` confirmation message

---

## 💼 Account APIs

### 🔹 Create Account

**POST** `/create-account`

- **Request Body**: `AccountRequestDto`
- **Response**: `AccountResponseDto`

### 🔹 Get Accounts by User

**GET** `/all-account?user_id={UUID}`

- **Query Param**: `UUID user_id`
- **Response**: `List<AccountResponseDto>`

---

## 💸 Transaction APIs

### 🔹 Create Transaction

**POST** `/transaction`

- **Request Body**: `TransactionRequestDto`
- **Response**: `TransactionResponseDto`

### 🔹 Transaction History

**GET** `/transaction-history?account_id={UUID}&page=0&size=10`

- **Query Param**: `UUID account_id`, pagination via `Pageable`
- **Response**: `Page<Transaction>`

---

## 📊 Report APIs

### 🔹 Generate Transaction Report

**GET** `/report?type={TransactionType}&start_date=YYYY-MM-DD&end_date=YYYY-MM-DD`

- **Optional**: Filter by `TransactionType`
- **Dates**: ISO format `YYYY-MM-DD`
- **Response**: `TransactionReportResponse`
