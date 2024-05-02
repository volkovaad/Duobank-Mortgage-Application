@api


Feature: POST /user API endpoint features


  Scenario: The API endpoint accepts a POST request to /login resource

    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 200
    And the JWT token from the response is stored



    Scenario: The API endpoint should require authentication via API key
      Given API key is not provided
      And the request "Content-type" header is set to "application/json"
      And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
      When I send a "POST" request to the endpoint "/login"
      Then the response log should be displayed
      Then the response status code should be 401
      And the JWT token from the response is stored
      And error message should be displayed

      Scenario: The API endpoint should return a JSON response and header

        Given the request is authenticated with a valid API key
        And the request "Content-type" header is set to "application/json"
        And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
        When I send a "POST" request to the endpoint "/login"
        Then the response log should be displayed
        Then the response status code should be 200
        And the JWT token from the response is stored
        And the response "Content-type" header should be "application/json; charset=UTF-8"

#        Scenario: Request must contain JSON payload with user's email and password
#
#          Given the request is authenticated with a valid API key
#          And the request "Content-type" header is set to "application/json"
#          And the request body is set to the following payload as
#          """
#          {
#            "email": "duothechtest@gmail.com",
#            "password": "696XR3dfTbf9W"
#          }
#          """
#          And request body payload must contain user's email and password
#          """
#          {
#            "email": "duothechtest@gmail.com",
#            "password": "696XR3dfTbf9W"
#          }
#          """


  Scenario: The request method is not POST

    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
    When I send a "PATCH" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 405
    And the JWT token from the response is stored



    Scenario: The request is missing the email or password fields, or if they are empty
      Given the request is authenticated with a valid API key
      And the request "Content-type" header is set to "application/json"
      And the request body is set to the following payload as
          """
          {

            "password": ""
          }
          """
      When I send a "POST" request to the endpoint "/login"
      Then the response log should be displayed
      And API responds with a 422 status code and an error message "Please fill in all required fields!"

      Scenario: The email address is not in a valid format
        Given the request is authenticated with a valid API key
        And the request "Content-type" header is set to "application/json"
        And the request body is set to the following payload as
          """
          {
            "email": "duothechtestgmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
        When I send a "POST" request to the endpoint "/login"
        Then the response log should be displayed
        And API responds with a 422 status code and an error message "Invalid Email Address!"



  Scenario: The invalid password
    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696Xbf9W"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    And API responds with a 422 status code and an error message "Invalid Password!"

  Scenario: API generates a temporary JWT token

    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 200
    And the JWT token from the response is generates and stored

  Scenario: The API responds with a 200 status code and a success message

    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    Then the response status code should be 200
    And Success message should be "You've successfully logged in!"

#    Scenario: The API response payload should be in the format
#
#      Given the request is authenticated with a valid API key
#      And the request "Content-type" header is set to "application/json"
#      And the request body is set to the following payload as
#          """
#          {
#            "email": "duothechtest@gmail.com",
#            "password": "696XR3dfTbf9W"
#          }
#          """
#      When I send a "POST" request to the endpoint "/login"
#      Then the response log should be displayed
#      Then the response status code should be 200
#      And The API response payload should be in the format
#     """
#    {
#    "success": true,
#    "message": "You've successfully logged in!",
#    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2xvYW5cL2FwaSIsImF1ZCI6Imh0dHA6XC9cL2xvY2FsaG9zdFwvbG9hblwvYXBpIiwiaWF0IjoxNzE0Njc3MzUxLCJleHAiOjE3MTQ2ODA5NTEsImRhdGEiOnsidXNlcl9pZCI6IjExOTYwIiwidHlwZSI6IjIifX0.xJdo80s91YaoQwmvbNmmAzHLVVXLYU-pf3ccFwWdHt8",
#    "token_type": "Bearer",
#    "expires_in": 3600
#    }
#     """
#


  Scenario: The response time for a single user request should be less than 1000ms

    Given the request is authenticated with a valid API key
    And the request "Content-type" header is set to "application/json"
    And the request body is set to the following payload as
          """
          {
            "email": "duothechtest@gmail.com",
            "password": "696XR3dfTbf9W"
          }
          """
    When I send a "POST" request to the endpoint "/login"
    Then the response log should be displayed
    And the respond time should be less than 1000




















