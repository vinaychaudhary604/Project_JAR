
# Kirana Store Transaction Management Service

This backend service is designed to empower Kirana stores in efficiently managing their transaction registers. The primary goal is to facilitate the daily tracking of both credit and debit transactions, providing a comprehensive solution for effective financial record-keeping.

**Table of Contents**
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
  - [Record Transaction](#record-transaction)
  - [Get Daily Report](#get-daily-report)
- [Usage](#usage)

## Getting Started

To use this service locally or integrate it into your project, follow the steps below:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/vinaychaudhary604/Project_JAR
   ```

2. **Navigate to the project directory:**

   ```bash
   cd Kirana_Store
   ```

3. **Build and run the project using your favorite IDE or with the following command:**

   ```bash
   ./mvnw spring-boot:run
   ```

   The service will be accessible at `http://localhost:8080`.

## Endpoints

### Record Transaction

- **Endpoint**: `POST /record`
- **Description**: Records a new transaction, capturing both credit and debit transactions.
- **Request Body**:
  ```json
  { 
    "amount": "Integer[100]",
    "currency": "String[USD]",
    "type": "String[DEBIT, CREDIT]"
  }
  ```
- **Response**: Returns the recorded transaction with converted currency.
- **Example**:
  ```bash
  curl --location 'localhost:8080/record' \--header 'Content-Type: application/json' \--data '{"amount": 100, "currency": "USD","type": "DEBIT"}'
  ```

### Get Daily Report

- **Endpoint**: `GET /daily_report`
- **Description**: Retrieves a daily report of all transactions within a specified date range.
- **Query Parameters**:
  - `start` (ISO date format): Start date of the report.
  - `end` (ISO date format): End date of the report.
- **Response**: Returns a map of transactions grouped by date.
- **Example**:
  ```bash
  curl --location --request GET 'localhost:8080/daily_report?start=2024-01-01&end=2024-01-12' \--header 'Content-Type: application/json' \--data '{ "start": "2024-01-01", "end": "2024-01-12" }'
  ```

## Usage

- Ensure the service is running locally.
- Utilize tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/) to interact with the API endpoints.