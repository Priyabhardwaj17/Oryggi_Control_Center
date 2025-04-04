# Oryggi Control Center - Web Automation Suite

This project is a Selenium-based Web Automation Suite for the **Oryggi Control Center** application.  
It follows **Page Object Model (POM)** design with **TestNG**, **ExtentReports**, and utility support for screenshot capture, email alerts, and more.

## 🛠 Tech Stack

- Java 8
- Selenium WebDriver
- TestNG
- Maven
- ExtentReports
- JavaMail API
- Apache Commons IO

## 📁 Project Structure

src/ ├── main/ │ ├── java/ │ │ └── com.oryggi/ │ │ ├── base/ │ │ ├── pages/ │ │ └── utils/ └── test/ └── java/ └── com.oryggi.tests/

markdown
Copy
Edit

## ✅ Features

- Login & Forgot Password Test Cases
- Screenshot on failure with listener integration
- Email utility to send test report + screenshots
- Extent Report generation with TestNG
- Maven managed dependencies
- Modular POM design

## ▶️ Run Tests

```bash
mvn clean test
📬 Reports & Screenshots
Screenshots: /screenshots/

Reports: /test-output/