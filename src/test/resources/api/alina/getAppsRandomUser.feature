@api
Feature: I want to be able to retrieve a list of mortgage applications for a specific user


  Scenario: user making the request is an administrator, that is, as type 1
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
  """
          {
            "email": "jane.sage@hotmail.com",
            "password": "Hello123"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 200
    And the JWT token from the response is stored

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/applications"
    Then the response log should be displayed
    Then the response status code should be 200
   And all applications should be returned

  Scenario: user making the request with no applications

    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
  """
          {
            "email": "emailNoApplication@gmail.com",
            "password": "Hello123"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 200
    And the JWT token from the response is stored

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/applications"
    And empty list should be returned