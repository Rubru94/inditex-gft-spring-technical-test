# Inditex - Technical Test

## [Requirements](./requirements.md) 🔗

## Description 📃

REST API developed with **Spring Boot** to query prices based on product, brand & date.

[Ports & adapters architecture (hexagonal)](https://medium.com/@edusalguero/arquitectura-hexagonal-59834bb44b7f) + [vertical slicing](https://www.youtube.com/watch?v=eNFAJbWCSww) is used.

![hexagonal-architecture](./hexagonal-architecture.png)

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

   Accessible on:

   📌 - http://localhost:8080/

## Docker 🐋

Execute [`docker-compose`](./docker-compose.yaml) file:

```bash
docker compose up -d
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

When app is running, navigate to:

📌 - http://localhost:8080/swagger-ui/index.html

## Tests 💊

To run the tests use the following command:

```bash
mvn test
```

## Sonar 🚦

Including sonar as a container in [`docker-compose`](./docker-compose.yaml) file.

If it is the first time you deploy the container, **user** & **pass** are `admin` and sonar will ask you to change the pass.

Accessible on:

📌 - http://localhost:9000/

- To generate the coverage report, **JaCoCo** plugin has been added to the [`pom.xml`](./pom.xml).

  Execute:

  ```bash
  mvn clean verify
  ```

  ```bash
  mvn jacoco:report
  ```

  This last command will generate the `target/site` directory with the report.

- It will be necessary to create a project in sonar and an associated token. Then execute sonar command similar to this:

  ```bash
  sonar-scanner \
  -Dsonar.projectKey=inditex-technical-test \
  -Dsonar.sources=src/main/java \
  -Dsonar.java.binaries=target/classes \
  -Dsonar.java.libraries=target/\*.jar \
  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=<generated-token>
  ```

<hr/>

![sonar-overview](./sonar-overview.png)

## Postman 📫

A [postman collection](./inditex-technical-test.postman_collection.json) is made available to interact with the application by consuming its endpoints in the simplest way.
