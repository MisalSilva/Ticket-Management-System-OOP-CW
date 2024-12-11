# A Real Time Ticket Management System

## Introduction
The Ticket Management System is a multi-threaded Java application designed to simulate a ticket queue management system. Vendors add tickets to a shared ticket pool, while customers retrieve tickets based on a defined rate. The system provides a controlled and synchronized environment for managing ticket distribution efficiently.

---

## Setup Instructions

### Prerequisites
Before running the application, ensure that you have the following installed:
- **Java Development Kit (JDK)** version 8 or later.
- A compatible Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or Visual Studio Code (optional but recommended).

### How to Build and Run the Application
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/MisalSilva/Ticket-Management-System-OOP-CW.git
   cd Ticket-Management-System-OOP-CW
---

## Usage Instructions

### Configuring the System
Upon starting the application, you will be prompted to configure the following parameters:

- Maximum Capacity for the Ticket Queue: The total number of tickets the pool can hold at any time.
- Total Tickets for Each Vendor: The number of tickets each vendor will contribute.
- Ticket Release Rate (in seconds): The rate at which each vendor adds tickets to the pool.
- Quantity to Buy: The number of tickets each customer will attempt to retrieve.
- Ticket Retrieval Rate (in seconds): The rate at which customers retrieve tickets from the pool

### Starting the System
Once the configuration is complete, the system will:

- Start vendor threads to add tickets to the pool at the specified rate.
- Start customer threads to retrieve tickets at the defined rate.
- Explanation of UI Controls
- Currently, the system operates as a command-line application. Follow the prompts displayed in the terminal to interact with the system.

### Explanation of UI Controls
Currently, the system operates as a command-line application. Follow the prompts displayed in the terminal to interact with the system.

## Features
- Multi-threaded simulation of vendors and customers interacting with a shared ticket pool.
- Synchronized ticket pool to avoid race conditions.
- Configurable ticket queue size, ticket release rate, and retrieval rate.
- Error handling to ensure valid user input.

## Future Enhancements
- Add a graphical user interface (GUI) for better user experience.
- Include persistent storage for ticket logs.
- Support dynamic addition of vendors and customers during runtime.
