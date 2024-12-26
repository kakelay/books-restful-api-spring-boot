Here is a `README.md` file documenting the code for the `Book` and `BookDetail` features. The structure is designed to be user-friendly and provide all necessary information.

---

# Library Management System

This project is a Spring Boot application for managing books in a library. It includes CRUD operations for managing both `Book` and `BookDetail` entities.

## Features

- **Manage Books**: Add, update, view, and delete books.
- **Detailed Book Information**: Store and manage additional details about books.
- **RESTful APIs**: Easy integration with frontend or other services.
- **Database Support**: Uses PostgreSQL as the database.

---

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: Database access.
- **PostgreSQL**: Database management system.

---

## Setup Instructions

### Prerequisites

- **Java 17** or later
- **Maven** for dependency management
- **PostgreSQL** installed and running

### Configuration

Update the `application.properties` file in the `src/main/resources` directory with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### Database Setup

Create the necessary database tables using the SQL scripts:

#### Book Table

```sql
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    published_year INTEGER
);
```

#### BookDetail Table

```sql
CREATE TABLE book_details (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    published_year INTEGER,
    description TEXT
);
```

---

## Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repo/library-management.git
   cd library-management
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

---

## API Endpoints

### Book Endpoints

- **GET** `/api/books` - Retrieve all books.
- **GET** `/api/books/{id}` - Retrieve a book by ID.
- **POST** `/api/books` - Add a new book.
- **PUT** `/api/books/{id}` - Update an existing book.
- **DELETE** `/api/books/{id}` - Delete a book.

### BookDetail Endpoints

- **GET** `/api/book-details` - Retrieve all book details.
- **GET** `/api/book-details/{id}` - Retrieve a book detail by ID.
- **POST** `/api/book-details` - Add new book details.
- **PUT** `/api/book-details/{id}` - Update existing book details.
- **DELETE** `/api/book-details/{id}` - Delete book details.

---

## Project Structure

```
src/
├── main/
│   ├── java/com/example/library/
│   │   ├── controller/
│   │   │   ├── BookController.java
│   │   │   └── BookDetailController.java
│   │   ├── model/
│   │   │   ├── Book.java
│   │   │   └── BookDetail.java
│   │   ├── repository/
│   │   │   ├── BookRepository.java
│   │   │   └── BookDetailRepository.java
│   │   ├── service/
│   │   │   ├── BookService.java
│   │   │   └── BookDetailService.java
│   │   └── LibraryManagementApplication.java
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/
    ├── java/com/example/library/
    └── LibraryManagementApplicationTests.java
```

---

## Example JSON Requests

### Create a Book

```json
POST /api/books
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Fiction",
  "publishedYear": 1925
}
```

### Create a BookDetail

```json
POST /api/book-details
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Fiction",
  "publishedYear": 1925,
  "description": "A novel set in the Jazz Age that examines the American dream."
}
```

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

Feel free to modify this `README.md` as needed!
