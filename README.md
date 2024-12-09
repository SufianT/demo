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
  - Borrow, reserve, and save books to a personalized list.
  - Notifications for due dates and reserved book availability.
  - View borrowing history and outstanding fines.

### **Admin Functionality**
- **Admin Login:**
  - Predefined admin accounts stored securely in a JSON file.
  - Authentication using an email, password, and a unique admin key.
- **Admin Features:**
  - Manage library inventory (add/remove/update books).
  - View borrowing statistics and user activity.
  - Notify users about fines or book availability.

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
   git clone <repository-url>
   cd library-management
2. **Run DemoApplication.java:**
3. **Start your local host on port 8080 to preview the website**

## **Usage**

### **How to Register and Log In**

### **Registering as a User**
1. Navigate to the **Registration Page**.
2. Fill in the required fields:
    - **Name**
    - **Email**
    - **Personnummer** (Swedish format: YYYYMMDDXXXX)
    - **Password**
3. Submit the form. If successful:
    - Your account will be registered.
    - You will be automatically logged in and redirected to the user dashboard.

---

### **Logging In as a User**
1. Navigate to the **Login Page**.
2. Enter your registered **email** and **password**.
3. Click the **Login** button.
4. Upon successful login:
    - Access the library to explore books.
    - Borrow books and manage your account, including viewing borrowing history and fines.

---

### **Logging In as an Admin**
1. Navigate to the **Admin Login Page**.
2. Use one of the predefined admin credentials from the `admins.json` file:

    ```json
    [
    {
        "name": "John",
        "email": "johndoe@gmail.com",
        "password": "1234",
        "id": "0001",
        "adminKey": "4000"
    },
    {
        "name": "Jane",
        "email": "janedoe@gmail.com",
        "password": "1234",
        "id": "0002",
        "adminKey": "4000"
    }
   ]
    ```

3. Enter:
    - **Email**
    - **Password**
    - **Admin Key**
4. Upon successful login:
    - Manage the library system.
    - Add new books to the library.
    - View and oversee user activities.

---

## **Adding Books as an Admin**
1. After logging in as an admin, navigate to the **Add Books Page**.
2. Fill in the required details about the book:
    - **Title**
    - **Author**
    - **Genre**
    - **ISBN**
    - **Publication Date**
3. Submit the form to add the book to the library's inventory.
4. The newly added book will be available for users to borrow.

---

## **Future Enhancements**
- Add **search and filtering features** for books by advanced criteria.
- Add a chatting system between user and admin for easier communication. 

