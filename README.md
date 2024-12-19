# **BookHaven - Library Management System**

## **Overview**
BookHaven is a library management system designed to streamline the borrowing, returning, and managing of books for both users and administrators. Built using Java with a Spring Boot backend and a modern web interface, BookHaven focuses on being model-heavy to align with object-oriented programming principles.

The project integrates core OOP concepts, design patterns, and follows a modular approach, ensuring scalability and maintainability.

---

## **Features**

### **User Functionality**
- **User Registration:**
  - Secure registration with unique identification using Swedish social security numbers.
  - Automatic token-based session management upon successful registration.
- **User Login:**
  - Secure login with email and password verification.
  - Persistent token-based authentication.
- **Library Features:**
  - Browse books by genre, title, and author.
  - Borrow and save books to a personalized list.
  - Notifications for due dates and reserved book availability.
  - Notifications for actions like borrowing and returning books.
  - Notifications alert users if the return date is approaching to avoid fines.
  - View borrowing history and outstanding fines.
  - **Search Functionality:**
    - Search for books by title, author, or ISBN.

### **Admin Functionality**
- **Admin Login:**
  - Predefined admin accounts stored securely in a JSON file.
  - Authentication using an email, password, and a unique admin key.
- **Admin Features:**
  - Manage library inventory (add books).
  - Assist users via built in chat system.
  - **Chat System:**
    - Communicate with users directly through the platform.
    - Simultaneously handle chats with multiple users to provide assistance.

---

## **Technology Stack**

### **Backend**
- **Java (Spring Boot):**
  - Core logic for user authentication, admin management, and book operations.
  - RESTful APIs for communication between the front-end and back-end.
- **Jackson Library:** Used for JSON serialization/deserialization for storing and reading user and admin data.

### **Frontend**
- **HTML, CSS, JavaScript, Bootstrap:**
  - User-friendly and responsive interface.
  - Dynamic actions, such as user login/logout and borrowing books.
- **LocalStorage:** Manages session tokens on the client-side.

---

## **System Architecture**

- **Model-View-Controller (MVC):**
  - **Model:** Represents core business logic, including `User`, `Admin`, and `Book`.
  - **View:** The web interface built with HTML, CSS, and Bootstrap.
  - **Controller:** Spring Boot controllers for handling API requests and coordinating between the model and view.
- **Design Patterns:**
  - Bridge pattern for modular and decoupled design.
  - Other relevant patterns as per system requirements.

---

## **Setup Instructions**

### **Prerequisites**
1. Java Development Kit (JDK 17 or higher).
2. Maven for dependency management.
3. A modern web browser for accessing the UI.

### **Steps to Run the Project**
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/SufianT/demo.git
   cd library-management
2. **Run DemoApplication.java:**
3. **Start your local host on port 8080 to preview the website**
