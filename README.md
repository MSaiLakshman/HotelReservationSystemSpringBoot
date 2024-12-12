# Hotel Management System

This is a **Hotel Management System** built with **Spring Boot**, **JSP**  and **MySQL** for managing room bookings, check-ins, check-outs, and room availability.

## Features

- **Room Booking**: Allows users to book rooms based on availability.
- **Room Availability Check**: Users can check if a room is available for booking.
- **Booking Management**: Managers can view, update, and delete bookings.
- **Vacating Rooms**: Users can vacate rooms after checkout, making them available for future bookings.
- **Room Types**: The system supports both AC and Non-AC rooms.
- **Check-out Process**: Users can check out, updating room availability and booking status.

## Technologies Used

- **Spring Boot**: For backend development and REST API creation.
- **MySQL**: For database management.
- **Lombok**: To reduce boilerplate code for entity classes.
- **Spring Data JPA**: For easy database interaction.
- **Spring Security**: For user authentication (if applicable).

## Setup Instructions

### Prerequisites

Before running the project, ensure you have the following installed:

- **Java 17+** (for Spring Boot)
- **Maven** (for building the project)
- **MySQL** (for database)
- **IDE** (IntelliJ IDEA, Eclipse, etc.)

### Clone the Repository

```bash
git clone https://github.com/your-username/HotelManagementSystem.git
```

### Database Setup

1. Create a database named `hotel_management_system` in MySQL.
   
   ```sql
   CREATE DATABASE hotel_management_system;
   ```

2. Update the **`application.properties`** file with your MySQL credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hotel_management_system
   spring.datasource.username=root
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

### Running the Application

1. Open the project in your IDE.
2. Build the project with Maven:

   ```bash
   mvn clean install
   ```

3. Run the application using:

   ```bash
   mvn spring-boot:run
   ```

4. The application should be accessible at [http://localhost:8080](http://localhost:8080).

### Endpoints

The following REST API endpoints are available:

- **POST /booking/book**: Book a room
  - **Request**: Room ID, User ID, Check-in/Check-out Time, Number of Guests, Total Price
  - **Response**: Success/Failure message

- **GET /booking/checkAvailability**: Check room availability
  - **Request**: Room ID
  - **Response**: Boolean (true if available, false otherwise)

- **POST /booking/vacate**: Vacate a room after checkout
  - **Request**: Booking ID
  - **Response**: Success/Failure message

- **GET /booking/getRoomType**: Get room type (AC/Non-AC)
  - **Request**: Room ID
  - **Response**: Room Type (AC or Non-AC)

- **POST /booking/checkout**: Checkout and update room availability
  - **Request**: Booking ID
  - **Response**: Success/Failure message

## Contributing

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### Additional Notes:
1. **JSP Pages**: If you have specific JSP pages for the UI, mention them here and describe their role in the application.
2. **Authentication**: If your system involves user login, you can add details about the authentication mechanism (e.g., Spring Security).
3. **Test Coverage**: If you have written tests, you can include instructions on how to run them.

By including the above sections, your `README.md` will be informative and provide anyone interested in your project with clear setup instructions, API documentation, and contribution guidelines.
