@api
Feature: api testing

#    GET USERS
  Scenario: Retrieve all users successfully

    When I send a GET request to '/users' with a valid API key
    And the request "Content-type" header is set to "application/json"
    Then the response status code should be 200
    And the response time should be less than 2000 ms
    And the response body should include all user data
    And the Content-Type header should have a value of "application/json"

    And the response should only include relevant fields such as
      | id          |
      | email       |
      | first_name  |
      | last_name   |
      | phone       |
      | image       |
      | type        |
      | created_at  |
      | modified_at |
      | zone_id     |
      | church_id   |
      | country_id  |
      | active      |

  Scenario: Invalid request method
#    When I send a POST request to '/users' with a valid API key
#    Then the response status code should be 405
#    And the response body should contain an error message "Invalid request method"
    Given the request is authenticated with a valid API key
    When I send a "PUT" request to the endpoint "/users"
    Then the response status code should be 405
    And the response body should contain an error message "Invalid request method"

  Scenario: Missing API key
    When I send a GET request to '/users' without providing an API key
    Then the response status code should be 401
    And the response body should contain an error message "Invalid or missing API Key. Provide a valid api key with 'api_key' query parameter."

  Scenario: Pagination to limit the number of results returned
    When I send a GET request to '/users' with a valid API key and a limit query parameter
    Then the response status code should be 200
    And the number of returned users should be less than or equal to the specified limit

Scenario: X-Total-Count
  When I send a GET request to '/users' without providing an API key
  And the X-Total-Count header should indicate the total number of users in the database



#  POST USER

Scenario: POST user
  Given the request is authenticated with a valid API key
  And the request "Content-type" header is set to "application/json"
  And the request body is set to the following
  When I send a "POST" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 201
  And the response time should be less than 2000 ms
  And store user id

    #  GET USER
  Given the request specification is reset
  Given the request is authenticated with a valid API key
  And query parameter has user id
  And the request "Content-type" header is set to "application/json"
  When I send a "GET" request to the endpoint "/user"
  Then the response log should be displayed
  Then the response status code should be 200
  And the response time should be less than 2000 ms

  #  PATCH USER
  Scenario: PATCH user
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And query parameter has user id 12417
    And the request body is changing to the following
    When I send a "PATCH" request to the endpoint "/user"
    Then the response log should be displayed
    Then the response status code should be 200

