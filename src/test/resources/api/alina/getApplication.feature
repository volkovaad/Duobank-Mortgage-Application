@api
Feature: As a loan officer, I want to be able to retrieve a list of mortgage applications for a specific user, so that I can review their application status and history.

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


  Scenario: Get specific application for a specific user

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the user  provide a valid mortgage id 3003
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/application"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json; charset=UTF-8"
    And the response time should be less than 1000 ms

    Scenario: negative - API key is not provided
      Given the request specification is reset
      And the user  provide a valid mortgage id 3003
      When I send a "GET" request to the endpoint "/application"
      Then the response status code should be 401
      And the response body should contain an error message "Invalid or missing API Key. Provide a valid api key with 'api_key' query parameter."

  Scenario: Invalid request no mortgage id
    Given the request specification is reset
    Given the request is authenticated with a valid API key
    When I send a "GET" request to the endpoint "/application"
    Then the response status code should be 400
    And the response body should contain an error message "Bad request. Mortgage id must be provided with 'id' query parameter"

  Scenario: Invalid request method
    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the user  provide a valid mortgage id 3003
    When I send a "POST" request to the endpoint "/application"
    Then the response status code should be 405
    And the response body should contain an error message "Method not allowed"

  Scenario: no token

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the user  provide a valid mortgage id 3003
#    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/application"
    Then the response status code should be 403

  Scenario: invalid mortgage id

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the user  provide a valid mortgage id 300374756
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/application"
    Then the response status code should be 404