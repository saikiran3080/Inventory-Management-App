# 🏬 Inventory Management System API

A backend-heavy **Spring Boot** application to manage and track products in a warehouse.  
This project provides a clean REST API for **CRUD operations** and **inventory control**, using **Spring Data JPA**, **H2 Database**, **Lombok**, and **ModelMapper**.

---

## 🚀 Features

### 🧩 Product Management
- Create, Read, Update, and Delete products  
- Each product includes:
  - `name`
  - `description`
  - `stock_quantity`
  - `low_threshold` (boolean indicator for low stock)

### 📦 Inventory Logic
- Prevents stock quantity from dropping below 0  
- Dedicated endpoints to:
  - Increase stock quantity  
  - Decrease stock quantity (with validation for insufficient stock)

### ⚙️ Technical Highlights
- Spring Boot 3.x
- Spring Data JPA (ORM)
- Lombok (reduces boilerplate code)
- ModelMapper (Entity ↔ DTO conversion)
- H2 Database (in-memory)
- Exception Handling using `@ControllerAdvice`
- RESTful design with proper HTTP response codes

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|-------------|
| Backend | Spring Boot, Java 17+ |
| Persistence | Spring Data JPA |
| Database | H2 (In-Memory) |
| Object Mapping | ModelMapper |
| Logging | SLF4J |
| Dependency Management | Maven |

---

## 🗂️ Project Structure
src/main/java/com/example/Inventory_Management_App/
│
├── config
|  └── AppConfig

├── controller/
│ └── ProductController.java

├── entities/
│ └── Product.java

├── dtos/
│ └── ProductDTO.java

├── service/
│ ├── ProductService.java
│ └── ProductServiceImpl.java

├── repositories/
│ └── ProductRepo.java

├── exception/
│ ├── GlobalExceptionHandler.java
│ ├── BadRequestException.java
│ └── NotFoundException.java

└── InventoryManagementApp.java
## ⚡ API Endpoints

### 🔹 Product CRUD

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/products` | Retrieve all products |
| `GET` | `/products/{id}` | Retrieve a product by ID |
| `POST` | `/products` | Add a new product |
| `PUT` | `/products/{id}` | Update an existing product |
| `DELETE` | `/products/{id}` | Delete a product by ID |

### 🔹 Inventory Management

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `PATCH` | `/products/{id}/increase?quantity=5` | Increase product stock |
| `PATCH` | `/products/{id}/decrease?quantity=3` | Decrease product stock (throws error if insufficient) |
| `GET` | `/products/threshold` | Get all products below threshold |

---

## 🧪 Example JSON

### Create Product
```json
POST /products
{
  "name": "Laptop",
  "description": "Dell XPS 15",
  "stock_quantity": 10,
  "low_threshold": false
}

Response
{
  "id": 1,
  "name": "Laptop",
  "description": "Dell XPS 15",
  "stock_quantity": 10,
  "low_threshold": false
}
```



| Exception                         | Example Response                                                            | Status |
| --------------------------------- | --------------------------------------------------------------------------- | ------ |
| `NotFoundException`               | `{ "error": "not_found", "message": "Couldn't find Product id: 5" }`        | `404`  |
| `BadRequestException`             | `{ "error": "bad_request", "message": "Insufficient stock quantity" }`      | `400`  |
| `MethodArgumentNotValidException` | `{ "error": "validation_error", "message": "fieldName must not be empty" }` | `400`  |

🗃️ Database Configuration (H2)

File: application.properties

spring.application.name=Inventory_Management_App
spring.datasource.url=jdbc:h2:mem:inventory
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

Access the H2 Console

URL → http://localhost:8080/h2-console

JDBC URL → jdbc:h2:mem:inventory

User → sa

Password → (leave blank)

📬 Postman Collection

You can import and test all APIs directly using Postman:

👉 Inventory Management API - Postman Collection -> 

You can import and test all APIs directly using Postman:

👉 Inventory Management API - Postman Collection:
https://web.postman.co/workspace/My-Workspace~2cd365d3-bc35-487b-8912-fcc6cb7f6084/collection/37099448-34d3b50a-5381-4950-8836-bca66ee727ef?action=share&source=copy-link&creator=37099448



👨‍💻 Author

Sai Kiran Vakada
Java Backend Developer

📧 saikiranvakada9@gmail.com

Linkedin : https://www.linkedin.com/in/sai-kiran-vakada-14539a1b6/

