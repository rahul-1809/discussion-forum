# 🏢 Company Forum

A full-stack web application built with the Spring Framework that allows users to share and discuss experiences about companies. This project serves as a practical demonstration of building a modern, database-driven web application using Java, Spring Boot, and Thymeleaf.

---

## ✨ Features

* **User Authentication:** Secure user registration and login system powered by Spring Security.
* **Post Management:** Logged-in users can create posts about their company experiences.
* **Commenting System:** Users can comment on posts to create discussion threads.
* **Responsive UI:** A clean, modern, and responsive user interface built with Bootstrap 5.
* **Data Persistence:** All data is stored and managed in a database via Spring Data JPA and Hibernate.

---

## 🛠️ Tech Stack

* **Backend:**
    * Java 17
    * Spring Boot 3
    * Spring MVC
    * Spring Data JPA
    * Spring Security
* **Frontend:**
    * Thymeleaf
    * Bootstrap 5
* **Database:**
    * H2 (File-Based)
    * Hibernate
* **Build Tool:**
    * Maven

---

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You will need the following software installed on your machine:

* **JDK 17 or newer:** [Download Link](https://www.oracle.com/java/technologies/downloads/)
* **Apache Maven:** [Download Link](https://maven.apache.org/download.cgi)
* **Git:** [Download Link](https://git-scm.com/downloads)
* An IDE like **IntelliJ IDEA** or **VS Code**.

### Installation & Running

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/rahul-1809/discussion-forum.git](https://github.com/rahul-1809/discussion-forum.git)
    ```

2.  **Navigate to the project directory:**
    ```sh
    cd company-forum
    ```

3.  **Run the application using Maven:**
    ```sh
    mvn spring-boot:run
    ```

4.  **Access the application:**
    * Open your web browser and navigate to `http://localhost:8080`.
    * The application should be running.

---

## ⚙️ Configuration

The main configuration is located in the `src/main/resources/application.properties` file.

```properties
# Make the database save to a file named 'forumdb' in a 'data' folder
spring.datasource.url=jdbc:h2:file:./data/forumdb

# Enable the H2 database console, accessible at http://localhost:8080/h2-console
spring.h2.console.enabled=true

# Tell Hibernate to create tables if they don't exist and update them if entities change
spring.jpa.hibernate.ddl-auto=update
