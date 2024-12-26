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
CREATE TABLE book (
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



INSERT INTO book (title, author, genre, published_year, price) VALUES
('អ្វីដែលបុរសចង់បាន', 'សុភ័គ យ៉ុម', 'គោលបំណង និងចិត្តវិញ្ញាណ', 2015, 15.50),
('សៀវភៅសុខភាពគ្រួសារ', 'សុខ ភីរុណ', 'សុខភាព', 2020, 2.00), -- Price for second book updated to 2.00
('សាសនាចិត្តវិទ្យា', 'ជុន ចាន់ថន', 'ចិត្តវិទ្យា', 2018, 18.00),
('ខ្សែពេជ្រចិត្តសាស្ត្រ', 'ស៊ាន សុវត្ថិ', 'ទស្សនវិជ្ជា', 2016, 17.50),
('បេះដូងចុះជ្រៅ', 'ប៊ុន ស្រីម៉ានី', 'ស្នេហា', 2017, 12.75),
('កូនប្រុសស្រុកស្រែ', 'យឹម សារឿន', 'និទាន', 1999, 10.50),
('វិញ្ញាសាចិត្តសាស្ត្រ', 'ជួន ចាន់រតនា', 'ចិត្តវិទ្យា', 2021, 22.00),
('អាជីវកម្មឆ្នើម', 'អ៊ឹង លីស', 'អាជីវកម្ម', 2019, 25.00),
('ដំណេកនៅលើទឹក', 'ហេង ស្រីពៅ', 'និទាន', 2008, 11.00),
('អណ្ដាតភ្លើង', 'ស៊ុន បញ្ញារិទ្ធ', 'វចនានុក្រម', 2002, 14.00),
('ស្នេហារបស់កវី', 'សុទ្ធ ស៊ត', 'កវី', 2003, 13.50),
('ចិត្តវាងវៃ', 'ប៉ិន វុទ្ធី', 'ចិត្តវិទ្យា', 2005, 16.00),
('បញ្ហាបរិស្ថាន', 'សុខ សារិន', 'វិទ្យាសាស្ត្រ', 2013, 19.00),
('គុណវិបត្តិដើម', 'ស៊ាង រិទ្ធ', 'កំណាព្យ', 2011, 12.00),
('ព្រោះអូនស្រលាញ់', 'តារា រិទ្ធី', 'ស្នេហា', 2012, 14.50),
('សាសនាប្រាសាទ', 'អ៊ូ សារ៉ែន', 'ប្រវត្តិសាស្ត្រ', 2007, 20.50),
('ដើមឈើនិងធម្មជាតិ', 'ប៊ុន សុភ័គ', 'ធម្មជាតិ', 2009, 13.75),
('ការចូលរួមសង្គម', 'វ៉ន សុភា', 'សង្គមវិទ្យា', 2018, 21.00),
('តូចចិត្ត', 'ពៅ ឧត្តម', 'វប្បធម៌', 2006, 11.50),
('សួស្តីខ្ញុំជាសិស្ស', 'សុខ ម៉ុនី', 'កុមារ', 2010, 9.75),
('អេកូភីលី', 'សុទ្ធ សិរី', 'បរិស្ថាន', 2014, 15.00),
('ក្តីសង្ឃឹមចុងក្រោយ', 'ហេង សុធាវី', 'ស្នេហា', 2004, 13.00),
('ព្រាងកើតនៅស្រុកខ្មែរ', 'យឹម សុខា', 'ប្រវត្តិសាស្ត្រ', 1995, 12.25),
('វិទ្យាសាស្ត្ររូបវិទ្យា', 'សុខ សុជាតិ', 'វិទ្យាសាស្ត្រ', 2016, 18.50),
('ជីវិតថ្មី', 'វាសនា សុភា', 'គោលបំណង', 2008, 16.00),
('ប្រវត្តិសាស្ត្របារាំង', 'អេម សុជាតិ', 'ប្រវត្តិសាស្ត្រ', 2003, 19.00),
('សត្វព្រៃនិងព្រៃឈើ', 'ស៊ិន ច័ន្ទរ៉ា', 'ធម្មជាតិ', 2012, 20.00),
('សៀវភៅភាសាបាលី', 'ប៊ន វាសនា', 'ភាសា', 2015, 17.00),
('ទឹកសមុទ្រនិងចលនា', 'សុខ សំអាត', 'ធម្មជាតិ', 2019, 22.00),
('ជីវិតខ្ញុំ', 'អ៊ូ វណ្ណា', 'អត្តចរិត', 2007, 10.00),
('សៀវភៅសំខាន់ៗ', 'សុខ សារ៉ាណា', 'កំណាព្យ', 2022, 25.50),
('ដំណើរលើភ្នំ', 'ហេង សុភ័ក្រ', 'និទាន', 1998, 8.75),
('ចំណេះដឹងសុទ្ធ', 'ជុំ វ៉ាន់សន', 'ចិត្តវិទ្យា', 2011, 15.50),
('អង្គរស្ពានព្រៃ', 'អ៊ិម សារឿន', 'ប្រវត្តិសាស្ត្រ', 2000, 14.00),
('វិស័យកសិកម្ម', 'សូ សារិទ្ធ', 'កសិកម្ម', 2017, 18.00),
('វិទ្យាសាស្ត្រកុំព្យូទ័រ', 'សុខ វ៉ាន់ដា', 'វិទ្យាសាស្ត្រ', 2014, 20.50),
('រូបភាពនៃមនុស្ស', 'ប៉ែន សុភាព', 'ទស្សនវិជ្ជា', 2019, 19.50),
('ការគិតវិជ្ជមាន', 'សុខ ច័ន្ទសារី', 'ចិត្តវិទ្យា', 2021, 21.50),
('សម្លេងនៃដួងចិត្ត', 'ប៊ុន សុវណ្ណ', 'ស្នេហា', 2010, 17.00),
('ដំណើរចូលរួម', 'ប៊ុន សារិទ្ធ', 'សង្គមវិទ្យា', 2005, 15.25),
('ផ្កានៅជ្រុងផ្ទះ', 'ហេង វុទ្ធី', 'និទាន', 2001, 13.00),
('សៀវភៅអប់រំ', 'សុខ សុធារិទ្ធ', 'គោលបំណង', 2006, 16.25),
('សៀវភៅប្រលោមលោក', 'វ៉ាន់ វ៉ាថា', 'និទាន', 2013, 18.75),
('ចង្វាក់ជីវិត', 'ពៅ សុភ័ក្រ', 'វប្បធម៌', 2018, 14.75),
('សៀវភៅគំនិត', 'សុខ ចាន់ធា', 'ចិត្តវិទ្យា', 2020, 22.50),
('ជីវិតបរិស្ថាន', 'សួន សារឿន', 'បរិស្ថាន', 2012, 21.00),
('ដំណើរទៅសមុទ្រ', 'សុទ្ធ វណ្ណារ៉ា', 'និទាន', 2016, 20.25),
('សៀវភៅវិទ្យាសាស្ត្រ', 'ស៊ុន វិជ្ជា', 'វិទ្យាសាស្ត្រ', 2009, 17.25);


--------------------------------------------------------------------------------------------------

INSERT INTO book_details (title, author, genre, published_year, description) VALUES
('អ្វីដែលបុរសចង់បាន', 'សុភ័គ យ៉ុម', 'គោលបំណង និងចិត្តវិញ្ញាណ', 2015, 'សៀវភៅនេះបង្ហាញពីអ្វីដែលបុរសចង់បាននៅក្នុងជីវិត និងស្នេហា។'),
('សៀវភៅសុខភាពគ្រួសារ', 'សុខ ភីរុណ', 'សុខភាព', 2020, 'ការអប់រំសុខភាពសម្រាប់គ្រួសារ និងបណ្ដុះបណ្ដាលគំនិតសុខភាពល្អ។'),
('សាសនាចិត្តវិទ្យា', 'ជុន ចាន់ថន', 'ចិត្តវិទ្យា', 2018, 'ការសិក្សាចិត្តវិទ្យាដើម្បីយល់ចិត្តមនុស្ស និងអភិវឌ្ឍចិត្តវិជ្ជមាន។'),
('ខ្សែពេជ្រចិត្តសាស្ត្រ', 'ស៊ាន សុវត្ថិ', 'ទស្សនវិជ្ជា', 2016, 'សៀវភៅស្ថិតក្នុងប្រភេទទស្សនវិជ្ជា ធ្វើឲ្យយល់ពីចិត្តសាស្ត្រថ្មី។'),
('បេះដូងចុះជ្រៅ', 'ប៊ុន ស្រីម៉ានី', 'ស្នេហា', 2017, 'រឿងស្នេហាដ៏ជ្រៅជាមូលហេតុនៃការផ្លាស់ប្តូរជីវិត។'),
('កូនប្រុសស្រុកស្រែ', 'យឹម សារឿន', 'និទាន', 1999, 'រឿងរ៉ាវសាមញ្ញអំពីជីវិតនៅស្រុកស្រែ។'),
('វិញ្ញាសាចិត្តសាស្ត្រ', 'ជួន ចាន់រតនា', 'ចិត្តវិទ្យា', 2021, 'សៀវភៅសិក្សាសម្រាប់អ្នកចង់រៀនអំពីចិត្តវិទ្យាជាខ្មែរ។'),
('អាជីវកម្មឆ្នើម', 'អ៊ឹង លីស', 'អាជីវកម្ម', 2019, 'សៀវភៅដើម្បីស្វែងយល់អំពីវិធីសាស្ត្ររកសុី។'),
('ដំណេកនៅលើទឹក', 'ហេង ស្រីពៅ', 'និទាន', 2008, 'រឿងរ៉ាវប្លែកៗដែលកើតមាននៅលើទឹក។'),
('អណ្ដាតភ្លើង', 'ស៊ុន បញ្ញារិទ្ធ', 'វចនានុក្រម', 2002, 'សៀវភៅសិក្សាអំពីបញ្ហាភាសានិងវចនានុក្រម។'),
('ស្នេហារបស់កវី', 'សុទ្ធ ស៊ត', 'កវី', 2003, 'ស្នេហាបានបង្អស់ដោយកូនកវី។'),
('ចិត្តវាងវៃ', 'ប៉ិន វុទ្ធី', 'ចិត្តវិទ្យា', 2005, 'វិធីសាស្ត្រល្អសម្រាប់អភិវឌ្ឍចិត្តវិជ្ជមាន។'),
('បញ្ហាបរិស្ថាន', 'សុខ សារិន', 'វិទ្យាសាស្ត្រ', 2013, 'សៀវភៅធ្វើឲ្យយល់បញ្ហាបរិស្ថានក្នុងសង្គម។'),
('គុណវិបត្តិដើម', 'ស៊ាង រិទ្ធ', 'កំណាព្យ', 2011, 'ការសរសេរកំណាព្យដែលបង្ហាញគុណវិបត្តិដើម។'),
('ព្រោះអូនស្រលាញ់', 'តារា រិទ្ធី', 'ស្នេហា', 2012, 'ស្នេហាកំដៅចិត្តដែលទទួលបានការគាំទ្រ។'),
('សាសនាប្រាសាទ', 'អ៊ូ សារ៉ែន', 'ប្រវត្តិសាស្ត្រ', 2007, 'សៀវភៅប្រវត្តិសាស្ត្រច្រើនដែលទាក់ទងនឹងសាសនា។'),
('ដើមឈើនិងធម្មជាតិ', 'ប៊ុន សុភ័គ', 'ធម្មជាតិ', 2009, 'សៀវភៅបណ្ដុះបណ្ដាលបរិស្ថាននិងធម្មជាតិ។'),
('ការចូលរួមសង្គម', 'វ៉ន សុភា', 'សង្គមវិទ្យា', 2018, 'វិធីសាស្ត្រចូលរួមសង្គមសម្រាប់ការអភិវឌ្ឍ។'),
('តូចចិត្ត', 'ពៅ ឧត្តម', 'វប្បធម៌', 2006, 'សៀវភៅស្តីអំពីតូចចិត្តនិងអវិជ្ជមាន។'),
('សួស្តីខ្ញុំជាសិស្ស', 'សុខ ម៉ុនី', 'កុមារ', 2010, 'សៀវភៅសិក្សាសម្រាប់កុមារដែលចូលរៀនថ្នាក់។'),
-- Repeat for 30 more records using similar structure --


╱◥██████◣
│∩│🪟▤│🪟│
🎄▓▆▇█▓🚪▓█▇🎄
127.0.0.1


