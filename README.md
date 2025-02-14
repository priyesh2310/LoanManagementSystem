## Loan Management System

## 📌 Project Overview
The **Loan Management System** is a Java-based application that facilitates managing loan records efficiently. It allows users to store, retrieve, update, and delete loan data from a MySQL database. The system is designed to handle loan applications by storing borrower details, loan types, amounts, interest rates, and other relevant information.

## 🚀 Features
- ✅ **Add New Loan**: Store borrower and loan details in the database.
- 🔍 **Search Loans**: Fetch loan records by borrower name or loan ID.
- 📜 **View All Loans**: Display all loan records stored in the database.
- 📝 **Update Loan Details**: Modify borrower credit scores and adjust interest rates accordingly.
- ❌ **Delete Loan Records**: Remove loan details of a specific borrower.

## 🏗️ Tech Stack
- **Java** (JDK 11+)
- **MySQL** (Relational Database)
- **JDBC** (Java Database Connectivity)
- **Maven** (Build Automation)

## 🛠️ Setup and Installation
### 1️⃣ Prerequisites
Ensure you have the following installed:
- [Java 11+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL](https://dev.mysql.com/downloads/)
- [Maven](https://maven.apache.org/download.cgi)


### 3️⃣ Configure Database
1. Open MySQL and run the following SQL script to create the database and table:
```sql
CREATE DATABASE IF NOT EXISTS LoanDB;
USE LoanDB;

CREATE TABLE IF NOT EXISTS loans (
    loanID INT AUTO_INCREMENT PRIMARY KEY,
    borrowerName VARCHAR(255) NOT NULL,
    loanType VARCHAR(100) NOT NULL,
    loanAmount DOUBLE NOT NULL,
    interestRate DOUBLE NOT NULL,
    loanTerm INT NOT NULL,
    creditScore INT NOT NULL,
    salary DOUBLE NOT NULL,
    age INT NOT NULL
);
```
2. Update the **DatabaseHandler.java** file with your MySQL credentials:
```java
private static final String URL = "jdbc:mysql://localhost:3306/LoanDB";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### 4️⃣ Build and Run the Project
```sh
mvn clean install
java -jar target/loan-management-system.jar
```

## 📌 Usage
### Adding a New Loan
```java
Loan loan = new Loan(new Borrower("John Doe", 750, 50000, 30), 100000, 5.5, 10);
DatabaseHandler.saveLoanToDB(loan, "Home Loan");
```
### Fetching Loan Records
```java
DatabaseHandler.fetchLoansFromDB();
```
### Updating Credit Score
```java
DatabaseHandler.updateCreditScore("John Doe", 800);
```
### Deleting Loan Record
```java
DatabaseHandler.deleteLoanByName("John Doe");
```

## 🤝 Contributing
Pull requests are welcome! Feel free to open an issue or suggest enhancements.

## 📞 Contact
For any inquiries or support, reach out to **priyeshkumar554@gmail.com**.

---
💡 *Happy Coding!* 🎯

