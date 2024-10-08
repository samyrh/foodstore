
---

# **Pizzer App 🍕**

**Overview:**  
Pizzer is a user-friendly web application designed to offer a seamless food ordering experience. With a wide variety of food options, including restaurant dishes, fast food, pizzas, and other delicacies, Pizzer ensures that users can effortlessly browse, order, and track their meals.

---

## **Features 🎨**

### **User Side 🍽️**
- **Browse Menu**: Users can explore different food categories, including:
  - **Restaurant Dishes**: Gourmet meals from top restaurants.
  - **Fast Food**: Quick and easy snacks like burgers, fries, and wraps.
  - **Pizzas**: Customize and order delicious pizzas with your choice of toppings.
  - **Other Delicacies**: Desserts, salads, and more options for all tastes.

- **Place Orders**: Easily select and order food, add items to your cart, and proceed to checkout with ease.
- **Order Tracking**: Users can track their orders in real-time and get updates on delivery status.
- **User Profiles**: Manage personal information, view order history, and save multiple delivery addresses.

---

## **Technologies Used 🛠️**

- **Spring Boot 3.3.1** 🌟: For backend development.
- **Spring Security** 🔒: For user authentication and security.
- **Hibernate ORM & JPA** 💾: To manage database interactions.
- **JWT** 🗝️: Token-based authentication for secure sessions.
- **MySQL** 🗄️: Relational database management system.
- **Thymeleaf** 🌐: Server-side template engine for rendering dynamic pages.
- **Maven** ⚙️: Dependency management and project build tool.
- **Bootstrap** 🎨: For responsive design and styling.

---

## **Configuration 📄**

### **Application Properties**
```properties
spring.application.name=foodstore
server.port=9092
spring.datasource.url=jdbc:mysql://localhost:3306/foodstore?createDatabaseIfNotExist=true
spring.jpa.hibernate.ddl-auto=update // create
spring.datasource.username=root
spring.datasource.password=12345
```

---

## **Installation 🛠️**

### **1. Clone the Repository 💻**
```bash
git clone https://github.com/samyrh/foodstore.git
cd foodstore
```

### **2. Set Up MySQL Database 🗄️**
- **Create a Database**:
  Use MySQL Workbench or the command line to create a new database (e.g., `foodstore`).

### **3. Load Initial Data 📥**
You can initialize the database either by SQL scripts or programmatically:

- **Using SQL Scripts**:
  Place `schema.sql` and `data.sql` files in the `db/` directory.

- **Using Initialization Code**:
  ```java
  @Bean
  public CommandLineRunner loadData(UserRepository userRepository) {
      return args -> {
          userRepository.save(new User("user", "user@example.com", "password"));
      };
  }
  ```

### **4. Build and Run the Application 🏗️**
```bash
mvn clean install
mvn spring-boot:run
```

---

## **Usage 🚀**

### **Access the Application 🌐**
- **User Side**: Access the app at [http://localhost:9092/](http://localhost:9092/).

### **Login 🔑**
- **User Login**: Use your registered credentials to browse and place food orders.

---

## **Contributing 🤝**
We welcome contributions! If you have suggestions, improvements, or bug fixes, feel free to open an issue or submit a pull request on GitHub.

---

## **License 📜**
This project is licensed under the MIT License.

---

## **Contact 📧**
- **GitHub Repository**: [Pizzer App Repository](https://github.com/samyrh/foodstore.git)

---
