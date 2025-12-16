# Life Outcome Simulator

A web-based life outcome simulation platform that allows users to explore how different decisions—such as career choices, habits, education, and financial planning—can affect long-term life outcomes. The simulator helps users visualize possible futures based on their inputs and decisions.

## Features

* **User Authentication**: Secure login and registration using JWT-based authentication.
* **Decision-Based Simulation**: Users make life decisions (career, habits, finances, education) and see simulated outcomes.
* **Outcome Visualization**: Displays projected outcomes such as career growth, financial stability, and overall life satisfaction.
* **User Profiles**: Each user has a profile storing simulation history and preferences.
* **Scenario Replay**: Users can modify decisions and re-run simulations to compare outcomes.
* **Responsive UI**: Clean and responsive user interface for smooth interaction across devices.

## Technologies Used

* **Frontend**:
  * HTML, CSS, JavaScript
  * Fetch API for backend communication
  * Responsive UI design

* **Backend**:
  * Java (Spring Boot)
  * RESTful APIs for authentication, simulation logic, and user data
  * JWT-based security
  * MySQL (or compatible relational database)

* **Version Control**:
  * Git and GitHub

## Installation

### Clone the repository

```bash
git clone https://github.com/NILESH-KUMAR-coder/Life-Outcome-Simulator.git
cd Life-Outcome-Simulator
````

### Backend Setup (Spring Boot)

1. Navigate to the backend directory.

```bash
cd backend
```

2. Build the project using Maven.

```bash
mvn clean install
```

3. Run the Spring Boot application.

```bash
mvn spring-boot:run
```

### Frontend Setup

1. Navigate to the frontend directory (if applicable).
2. Open `index.html` using a live server or any static server.

```bash
# Example using live-server
live-server
```

### Running the Application

* The backend runs at `http://localhost:8080`
* The frontend communicates with the backend using REST APIs
* Make sure the backend is running before starting the frontend

## Configuration

This project uses **environment variables** for sensitive configuration values.

### Required Environment Variables

Before running the backend, ensure the following variables are set:

* **DB_PASSWORD** — Database password
* **JWT_SECRET** — Secret key for signing JWT tokens

Example (PowerShell on Windows):

```powershell
$env:DB_PASSWORD="your_database_password"
$env:JWT_SECRET="your_jwt_secret"
```

Example (Linux / macOS):

```bash
export DB_PASSWORD="your_database_password"
export JWT_SECRET="your_jwt_secret"
```

Sensitive values are **not committed to the repository** for security reasons.

## Contributing

1. Fork this repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-name`).
6. Open a Pull Request.

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
