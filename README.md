# EcommerceAppUsingSpringBootMonolithicArchitecture


This repository contains the source code for a Spring Boot-based E-commerce application with MongoDB as the database. The project encompasses user authentication, category management, and product handling through a set of well-defined API endpoints.

## API Endpoints:

1. **SignUp Endpoint:** `/user/registration`
   - Allows users to register for the E-commerce platform.

2. **Login Endpoint:** `/user/login`
   - Enables users to log in to their accounts securely.

3. **Add Category Endpoint:** `/category/add`
   - Facilitates the addition of new product categories.

4. **Update a Category with ID and Any Field Endpoint:** `/category/update/{id}`
   - Allows updating category details by specifying the category ID.

5. **Delete a Category with ID Endpoint:** `/category/del/{id}`
   - Deletes a specific category based on the provided ID.

6. **Add a Product Endpoint:** `/product/add`
   - Adds a new product to the E-commerce inventory.

7. **Link Product with Category ID Endpoint:** `/product/link/{prodId}/{catId}`
   - Establishes a link between a product and its associated category.

8. **Update a Product ID and Any Field Endpoint:** `/product/update/{id}`
   - Permits updating product details by specifying the product ID.

9. **Delete a Product with ID Endpoint:** `/product/del/{id}`
   - Deletes a specific product based on the provided ID.

10. **Get All Products Endpoint:** `/product/getAllProduct`
    - Retrieves a list of all available products.

11. **Get Product by Any Field Endpoint:** `/product/getByFieldValue`
    - Retrieves product information based on specified criteria.

## Database Configuration:

- MongoDB is utilized as the primary database for this project.

## How to Run:

1. Clone the repository to your local machine.
2. Configure the MongoDB connection details in the application.properties file.
3. Run the Spring Boot application.

Feel free to explore and contribute to the development of this E-commerce application! If you encounter any issues or have suggestions, please open an issue or submit a pull request.
