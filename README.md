# E-Space ( E-Commerce platform for space equipment sales)

The project is an E-Commerce site developed in Node.js and Express.js, which uses communication between microservices through REST APIs for filtering and data management. Using a gateway as an intermediary for communication with existing microservices in the application.

imgs/e-space.gif

### The microservice responsible for handling the application's business logic was developed using the following technologies:

- Programming language: Java
- Framework: SpringBoot
- Additional Spring libraries/frameworks: Spring Data JPA, Spring Web
- Database: PostgreSQL (using JDBC driver)
- Build Management Dependencies: Maven

This microservice works as a CRUD (Create, Read, Update, Delete) to handle products in an e-commerce system. Here is a brief description of how it works:

1. The microservice receives HTTP requests to perform CRUD operations on products.
2. Through the `@RestController` annotation, define the API endpoints to handle the requests.
3. The `/api/2/new` endpoint receives a POST request with the data of a new product in the form of a `ProductDto` object. It converts the `ProductDto` into a `Product` object and saves it to the repository via the `ProductRepository` class.
4. The `/api/2/find` endpoint takes a GET request and returns a list of all products in the repository. It gets the products from the repository through the `ProductRepository` class and converts each `Product` object into a `ProductDto` object.
5. The `/api/2/find/{id}` endpoint receives a GET request with an ID parameter to find a specific product in the repository. It searches the repository for the corresponding product by ID via the `ProductRepository` class and returns the found product as a `ProductDto` object.
6. The service uses the `ProductDto` and `Product` classes to perform the conversion between domain entities and data transfer objects.
7. CRUD operations are implemented in the `ProductService` service, which uses the `ProductRepository` to access the product repository and perform operations on the database.
8. The `@Service` annotation marks the `ProductService` class as a Spring service component.
9. The microservice is built using the Spring Boot framework and is deployed as a standalone service, allowing integration with other components and systems of a larger e-commerce ecosystem.

Overall, this microservice provides an interface to create, read, update, and delete products in an e-commerce system, using the CRUD pattern to manage product operations.

### For data filtering, a microservice was developed using the following technologies:

- Programming language: Python
- Framework: Flask
- Additional libraries: requests (to make HTTP requests), jsonify and make_response (from the flask module) for manipulation and response of data in JSON format.

1. The microservice defines a /datareturn/<typeFilter> route using Flask's @app.route() decorator. This route expects to receive a <typeFilter> parameter in the URL.
2. When a GET request is made for this route, the dataReturn() function is executed.
3. Inside the dataReturn() function, the <typeFilter> parameter is passed as an argument to the dataReturnType() function of the productService module of the Service package.
4. The dataReturnType() function is responsible for making a GET request to an external API specified by the url variable.
5. The API response is stored in a variable called response_list and converted into a dictionary called response_dict.
6. It then loops over each key and value in the response_dict dictionary.
7. Inside the loop, it checks if the value of the 'type' key in descriptionProduct matches the filter specified by the <typeFilter> parameter.
8. If there is a match, the relevant data from the filtered result is stored in variables such as ind, imgCard, end, price and name.
9. The filtered data is added to a dictionary called response.
10. The values from the response dictionary are converted into a list called values_list.
11. The values_list is returned as a JSON-formatted HTTP response using the make_response(jsonify(values_list)) function.
12. The microservice runs on a local Flask server when the file is executed.

In short, the microservice takes a filter as a parameter in the URL, makes a request to an external API, filters the results based on the provided filter, and returns the filtered results as a JSON response.

### Communication with these microservices is intermediated by a gateway that was developed using the Spring Cloud Gateway framework

https://github.com/ThiagoJv-pro/Site-ECommerce_E-Space/blob/master/imgs/E-Space_MS_Architecture.png?raw=true
