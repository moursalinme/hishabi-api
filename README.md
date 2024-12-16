<div align="center">
    <h1>HISHAB RAKHUN API </h1>
    <h3> Keep track of your finance </h3>
</div>

# Table of Contents

- [Table of Contents](#table-of-contents)
  - [About Hishab Rakhun](#about-hishab-rakhun)
  - [Getting Started](#getting-started)
    - [With Docker](#with-docker)
      - [Prerequisites](#prerequisites)
      - [Installation](#installation)
    - [Without Docker](#without-docker)
      - [Prerequisites](#prerequisites-1)
      - [Installation](#installation-1)
      - [Notes](#notes)
  - [Features](#features)
  - [Conclusion](#conclusion)

## About Hishab Rakhun

Hishab Rakhun is a personal finance management application that simplifies tracking your financial journey.
Unlike complex financial tools, this app makes recording income and expenses as intuitive as taking a note.
Capture every financial moment with ease – log how you earn money, when you receive it, and through what transactions.
Similarly, track your spending with rich, contextual details. Hishab Rakhun automatically generates a comprehensive
overview of your financial landscape, showing your total earnings, expenses, and remaining balance in real-time.

## Getting Started

### With Docker

#### Prerequisites

-   Must have **Docker** installed on your computer. You can install it from [here](https://docs.docker.com/engine/install/).
-   Start the Docker Engine on your computer.

#### Installation

1. Clone the repository

    ```sh
    git clone https://github.com/moursalinme/hishabi-api.git
    ```

2. Navigate to the project folder

    ```sh
    cd hishabi-api
    ```

3. Run the follwing command within the directory. It will start at **port 8080**. You can change the port in the configuration if needed.

    ```sh
    docker-compose up
    ```

### Without Docker

#### Prerequisites

1. **Java Development Kit (JDK)**

    - Version: **Java 21**

2. **Database** (if applicable)
    - Example: **PostgreSQL**
    - Ensure the database server is up and running.

#### Installation

1.  Clone the repository

    ```sh
    git clone https://github.com/moursalinme/hishabi-api.git
    ```

2.  Navigate to the project folder

    ```sh
    cd hishabi-api
    ```

3.  Setup the database, ports it should run navigating to file below. And configure the swagger api route.

    ```sh
    cd hishabi-api/src/main/resources/application.yml
    ```

4.  Execute the following command from the project root directory `/hishabi-api` using a terminal like Git Bash:

    ```sh
    ./mvnw spring-boot:run
    ```

#### Notes

-   The application will start on port 8080 by default. Update application.yml if you need to change this.
-   Access the Swagger API documentation route after starting the server. (You can add the exact URL for Swagger here).
-   You can access the **API DOCS** by hitting the endpoint `/api/v1/swagger-ui/index.html`

## Features

-   **Effortless Transaction Logging**: Capture every financial moment with simple, quick entry
-   **Comprehensive Income Tracking**: Record how, when, and through what channels you earn money
-   **Detailed Expense Management**: Log spending with rich context and categorization
-   **Real-Time Financial Insights**: Instant overview of earnings, spendings, and remaining balance
-   **Intelligent Visualization**: Understand your financial health at a glance

## Conclusion

Hishab Rakhun is your financial companion on the path to financial clarity and control. By simplifying complex financial tracking, we empower you to make informed decisions, understand your spending patterns, and take charge of your financial future.

Whether you're a student managing a tight budget, a freelancer tracking irregular income, or a professional looking to optimize your finances, Hishab Rakhun adapts to your unique financial journey. Every transaction tells a story, and we're here to help you read it clearly.

**Start your financial transformation today. Track, understand, and grow with Hishab Rakhun.**
