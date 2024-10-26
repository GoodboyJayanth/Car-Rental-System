# Car-Rental-System
The Car Rental System project in Java is a console-based application that simplifies the process of renting and returning cars. Designed with a straightforward user experience, this project enables users to view available cars, rent a selected car for a specified number of days, and return cars once they're done. For each rental, the system calculates the total price based on the car's daily rental rate, providing customers with a transparent breakdown of costs before confirming a rental. The system also tracks car availability in real time, ensuring that only available cars can be rented and automatically marking them as unavailable once rented.


Classes and Structure:![carrental flowchart](https://github.com/user-attachments/assets/c1460a69-9f90-49c0-a571-29dcce8640a9)

Car class:
Encapsulates properties of each car (carId, brand, model, basepriceperday, isavailable).
Provides methods to check availability, calculate rental cost, rent, and return the car.
Customer class:
Stores customer details (customername, customerid), assigning a unique ID to each customer.
Rental class:
Acts as a relationship bridge between Car and Customer, tracking the car rented, the customer who rented it, and rental duration in days.
CarRentalSystem class:
Manages lists of cars, customers, and rentals.
Contains methods to add cars, customers, and manage rentals and returns.
Core OOP Concepts:

Encapsulation:
Each class has private fields with public getter methods, ensuring data hiding.
Composition:
CarRentalSystem class utilizes Car, Customer, and Rental objects, modeling a system where rental records are composed of car and customer data.
Polymorphism:
Methods are used flexibly across classes (rent(), returncar(), menu()), allowing interactions with different data types effectively.
Interactive Console Menu:

menu() method:
Provides options to rent a car, return a car, or exit.
Prompts for customer name, displays available cars, calculates rental costs, and confirms transactions.
Error Handling:

Checks for car availability and validates user inputs to ensure accurate and reliable rental processing.
Implementation Highlights:

Customer ID is automatically generated for each new customer.
Cars are marked available or unavailable based on rental status.
Total rental cost is calculated dynamically based on the number of days and the car's base rate.
System Extensibility:

The design allows easy extension, as new cars, customers, or system functionalities can be added without major code restructuring.
