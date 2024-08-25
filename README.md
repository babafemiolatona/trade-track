# Trade-Track

TradeTrack is a comprehensive e-commerce API built using Spring Boot. It provides a set of features for managing products, user authentication, shopping carts, and orders.

## Features

* **Product Management**: Add, update, delete and view products.
- **Cart Management**: Add and remove items; calculate totals.
* **User Authentication**: Register, login and secure access with JWT.
+ **Order Management**: Create and view orders.
- **Role-Based Access control**: Admins can manage products and view all orders, while customers can manage their own orders and cart.
+ **Search and filter products.**

## Technologies Used

- Java
+ Spring Boot(Java Framework)
- Hibernate/JPA: For ORM(Object-Relational Mapping) and database interactions.
* PostgreSQL
+ Maven: For project build and dependency management.
- Swagger: For API documentation.
+ JWT(JSON Web Tokens): For secure user authentication.

## Installation

1. Clone the repository:
  ```
  git clone https://github.com/babafemiolatona/trade-track.git
  ```
2. Navigate to the project directory:
  ```
  cd trade-track
  ```
3. Set up the PostgreSQL database:
  - Create a new database.
  + Configure the database connection in `src/main/resources/application.properties`.
4. Install dependencies and build project:
  ```
  mvn clean install
  ```
5. Run the application:
  ```
  ./mvnw spring-boot:run
  ```
6. The API is available at:
  ```
  http://localhost:8080/swagger-ui/index.html
  ```
## Configuration
Set up the following environment variables or update the `application.properties` file:
- **`DB_NAME`**: Database name.
+ **`DB_USER`**: PostgreSQL username.
* **`DB_PASSWORD`**: PostgreSQL password.

## Usage
### API Endpoints
Here are some key endpoints:
1. **Product Management**:
  - **GET `/api/v1/products`** - View all products.
  + **POST `/api/v1/products`** - Add a new product (Admin only).
  * **PUT `/api/v1/products/{id}`** - Update product details (Admin only).
  + **DELETE `/api/v1/products/{id}`** - Delete a product (Admin only).
2. **User Authentication**:
  - **POST `/api/v1/auth/register`** - Register a new user.
  + **POST `/api/v1/auth/login`** - Login and get JWT.
3. **Cart Management**:
  - **GET `/api/v1/cart`** - View cart items.
  + **POST `/api/v1/cart/add`** - Add a product to the cart.
  * **DELETE `/api/cart/remove/{productId}`** - Remove a product from the cart.
