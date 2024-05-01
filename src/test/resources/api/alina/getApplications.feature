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


  Scenario: Get applications for a specific user

    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/applications"
    Then the response log should be displayed
    Then the response status code should be 200
    And the response "Content-Type" header should be "application/json; charset=UTF-8"
    And the response time should be less than 1000 ms
    And appid should be stored and list of mortgage applications must sort by creation date or appID

    Scenario: list of applications
      Given the request specification is reset
      Given the request is authenticated with a valid API key
      And the request "Content-type" header is set to "application/json"
      And the JWT token is set in the header
      When I send a "GET" request to the endpoint "/applications"
    And must return a list of mortgage applications with the following information for each application
      | Application ID       |
      | Borrower First Name  |
      | Borrower Last Name   |
      | Borrower Middle Name |
      | Total Loan Amount    |

  Scenario: soring by creation date
    Given the request specification is reset
    Given the request is authenticated with a valid API key
    And the JWT token is set in the header
    And list of mortgage applications must be ordered by creation date
    And the request "Content-type" header is set to "application/json"
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/applications"
    And extract creation date from database and it shouldn't be null


  Scenario:  only mortgage applications associated with the user's user ID must be returned
    Given the request specification is reset
    Given the request is authenticated with a valid API key
    Given the user id is provided
    And the JWT token is set in the header
    When I send a "GET" request to the endpoint "/applications"
    Then amount of applications should be equals the applications for this specific user