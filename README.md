# Order Service (Microservices)

## Overview

This service handles order creation and communicates with Inventory Service to validate product availability before placing an order.

## Tech Stack

* Java
* Spring Boot
* REST APIs
* PostgreSQL
* JPA / Hibernate

## Features

* Create Order API
* Get Order by ID
* Calls Inventory Service for stock validation
* Exception handling

## API Endpoints

POST /orders
GET /orders/{id}

## How to Run

1. Start PostgreSQL
2. Create database: order_db
3. Run Spring Boot application
