# Inditex - Technical Test

## [Requirements](./requirements.md) 🔗

## Description 📃

REST API developed with **Spring Boot** to query prices based on product, brand & date.

## Stack ⛺

- Java 21
- Spring Boot
- H2 Database
- JPA
- Swagger
- Docker

## Setup 🔨

1. Clone repository:

   ```bash
   git clone <repository-url>
   cd inditex-gft-spring-technical-test
   ```

2. Build project:

   ```bash
   mvn clean install
   ```

3. Run application:
   ```bash
   mvn spring-boot:run
   ```

## API 📌

- **GET /api/prices**:

  - **Description**: Gets one price according to brandId, productId & applicationDate.
  - **Query params**:

    - `brandId` (Integer) - Id of the brand.
    - `productId` (Integer) - Id of the product.
    - `applicationDate` (LocalDateTime) - Date within the date range in which a price is applicable.

  - **Example**:

    ```bash
      curl -X GET "http://localhost:8080/api/prices?brandId=1&productId=35455&applicationDate=2020-06-14 10:00:00"
    ```

  - **Response**: **_Price_** object response containing the price detail.

    ```json
    {
      "id": 1,
      "brandId": 1,
      "startDate": "2020-06-14T00:00:00",
      "endDate": "2020-12-31T23:59:59",
      "priceList": 1,
      "productId": 35455,
      "priority": 0,
      "price": 35.5,
      "currency": "EUR"
    }
    ```

## Swagger UI 👀

Swagger UI allows anyone to visualize and interact with the API’s resources without having any of the implementation logic in place:

📌 - http://localhost:8080/swagger-ui/index.html

## Tests 💊

To run the tests use the following command:

```bash
mvn test
```
