
# Kirana Store Transaction Management Service

This backend service is designed to empower Kirana stores in efficiently managing their transaction registers. The primary goal is to facilitate the daily tracking of both credit and debit transactions, providing a comprehensive solution for effective financial record-keeping.

**Table of Contents**
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
  - [Record Transaction](#record-transaction)
  - [Get Daily Report](#get-daily-report)
  - [Update Transaction By Id](#update-transaction-by-id)
- [Database Connection and Assumptions](#database-connection-and-assumptions)
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
## Endpoint

### Update Transaction By Id
- **Method:** `PUT`
- **Endpoint:** `/{transactionId}`
- **Description**: This documentation provides details about the `updateTransaction` API, which is designed to update an existing transaction by its ID.

**Path Parameter**
- `transactionId` (Long): The unique identifier of the transaction to be updated.
 **Request Body**
- `updatedTransaction` (Transaction): .
 PUT /transactions/{transactionId}
Content-Type: application/json
```json
  {
    "amountINR": 1500.00,
    "currencyINR": "INR",
    "amountUSD": 20.50,
    "currencyUSD": "USD",
    "type": "Credit"
  }
```
**Response**: Update the Amount in the Database.
- **Example**:
```bash
curl --location --globoff --request PUT 'localhost:8080/{transactionId}?transactionId=0' \--header 'Content-Type: application/json' \--data '{
    "amountINR": 90.96,
    "currencyINR": "INR",
    "amountUSD": 40,
    "currencyUSD": "USD",
    "type": "DEBIT"
}'
```

## Database Connection and Assumptions

### Database Connection

The service uses the `application.properties` file for database configuration. Please ensure you have the following properties set in the file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jar_api
spring.datasource.username=root
spring.datasource.password=password
```

### Assumptions

- The `Transaction` model assumes the following structure:
  ```java
  public class Transaction {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private LocalDateTime timestamp;
      private BigDecimal amountINR;
      private String currencyINR;
      private BigDecimal amountUSD;
      private String currencyUSD;
      private TransactionType type; 
  }
  ```

- Assumption for transaction types:
  - `DEBIT`: Represented as `0`.
  - `CREDIT`: Represented as `1`.

## usage

- Ensure the service is running locally.
- Utilize tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/) to interact with the API endpoints.
