@api
Feature:As a user I want to be able to retrieve a list of mortgage applications for a specific user




  Background:
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
  """
          {
            "email": "%s",
            "password": "%s"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 200
    And the JWT token from the response is stored


  Scenario: Get applications for a specific user

    And the user provides a valid mortgage ID as a query parameter
    When the user sends a "GET" request to the endpoint "/application"
    And the user  provide a valid mortgage id 3266
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json"
    And the response contains details of the mortgage application
    And the response time should be less than 1000 ms

  Scenario: Retrieve details of a single mortgage application with missing Api
    Given the user does not provide a valid JWT token
    And the user  provide a valid mortgage id 3266
    When the user sends a "GET" request to the endpoint "/application"
    Then the response status code should be 401
    And the response "Content-Type" header should be "application/json"
    And the response contains an error message indicating a forbidden access

  Scenario: Retrieve details of a single mortgage application with invalid Id
    Given the user provides an invalid JWT token
    When the user sends a "GET" request to the endpoint "/application"
    And the user  provide a valid mortgage id 5007756
    Then the response status code should be 400
    And the response "Content-Type" header should be "application/json"
    And the response contains an error message indicating a forbidden access

  Scenario: Retrieve details of a single mortgage application with missing mortgage ID
    Given the user provides an empty mortgage ID
    When the user sends a "GET" request to the endpoint "/application"
    And the user  provide a valid mortgage id 3266
    Then the response status code should be 405
    And the response "Content-Type" header should be "application/json"

  Scenario: Retrieve details of a single mortgage application with non-existent mortgage ID
    Given a valid JWT token is provided in the 'Authorization' header
   And a non-existent mortgage ID is provided as a query parameter
   When the user sends a "GET" request to the endpoint "/application"
    And the user  provide a valid mortgage id 3266
   Then the response status code should be 404
    And the response "Content-Type" header should be "application/json"