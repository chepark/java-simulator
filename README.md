# Supermarket Simulator Project

This Supermarket Simulator is a Java-based application utilizing JavaFX for the graphical interface and MySQL for
database management. The project employs multithreading techniques and leverages the Poisson distribution for simulating
customer arrivals.

<iframe width="560" height="315" src="https://youtu.be/_EMkGNqOkQY?si=TpGKNYVAiALI25ED" frameborder="0" allowfullscreen></iframe>

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Testing](#testing)
- [Group members](#group-members)

## Overview

The Supermarket Simulator is designed to replicate customer flow dynamics within a supermarket environment. It utilizes
JavaFX for the user interface, enabling users to interact with the simulation. Leveraging the Poisson distribution, the
application generates customer arrival patterns, while multithreading techniques facilitate concurrent processing for
enhanced simulation accuracy.

## Tech Stack

- Java
- JavaFX
- MySQL
- JUnit

## Features

- [x] Changes the Poisson distribution with user input to simulate different customer arrival rates.
- [x] Graphical representation of the supermarket environment using JavaFX.
- [x] Database integration with MySQL for data storage and retrieval
- [x] JUnit tests
- [x] JavaDoc documentation: files are located in the root directory of the project under the `JavaDoc` folder
- [x] Multithreading for concurrent processing and enhanced simulation accuracy

## Setup and Installation

1. Clone this repository.
2. Ensure Java and MySQL are installed.
3. Set up the MySQL database using the provided sql script `./simulator.sql`.
4. Compile the Java code using your preferred IDE or command line.
5. Run `src/main/java/app/simulator/views/App.java` to start the simulator.

## Usage

Upon running the application, users can input λ values via the JavaFX interface to simulate different customer arrival
rates. The graphical interface displays the supermarket environment, visualizing customer queues and service points in
real-time.

## Testing

The project includes JUnit tests for various functionalities, ensuring the accuracy and reliability of the simulation
components.

To run tests:

```bash
$ mvn test
```

## Group members
* [Bijay Karki](https://github.com/BijayKarki)
* [Chaeah Park](https://github.com/chepark)
* [Mamita Gurung](https://github.com/Mamita123)
* [Vladislav Zakatov](https://github.com/VlazaIT)
