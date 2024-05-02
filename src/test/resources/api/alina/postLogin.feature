@api
  Feature: As a user of the mortgage application, I want to be able to login using my email and password through an API endpoint, so that I can access my personal information and apply for a mortgage loan.

    Scenario: post/login successfully
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
  And the response time should be less than 1000 ms
  And the response body should contain a successfull message "You've successfully logged in!"
  And the response body should contain the following text
"""
{"success":true,"message":"You've successfully logged in!","access_token":"%s","token_type":"Bearer","expires_in":3600}
"""

    Scenario: Missing API key post/login
      When I send a "POST" request to the endpoint "/login"
      Then the response status code should be 401
      And the response body should contain an error message "Invalid or missing API Key. Provide a valid api key with 'api_key' query parameter."

#      unseccessful - BUG - 200 body 404
    Scenario: Invalid request method
      Given the request is authenticated with a valid API key
      And the request body is set to the following payload as
      """
      {
        "email": "%s",
        "password": "%s"
      }
      """
      When I send a "PUT" request to the endpoint "/login"
      And the response body should contain an error message "Invalid request method"
      Then the response status code should be 405

      # unseccessful scenario - 200 response but error message
    Scenario: post/login with missing email information
      Given the request is authenticated with a valid API key
      And the request body is set to the following payload as
  """
          {
            "password": "%s"
          }
          """
      When I send a "POST" request to the endpoint "/login"
      Then the response log should be displayed
      And the response body should contain an error message "Please fill in all required fields!"
      Then the response status code should be 422

    Scenario: post/login with missing password information
      Given the request is authenticated with a valid API key
      And the request body is set to the following payload as
  """
          {
        "email": "%s",
          }
          """
      When I send a "POST" request to the endpoint "/login"
      Then the response log should be displayed
      And the response body should contain an error message "Please fill in all required fields!"
      Then the response status code should be 422


    Scenario: post/login with invalid email information
      Given the request is authenticated with a valid API key
      And the request body is set to the
  """
          {
        "email": "duoytfbkknv@hotmail.co",
        "password": "696XR3dfTbf9W"
          }
 """
      When I send a "POST" request to the endpoint "/login"
      Then the response log should be displayed
      And the response body should contain an error message "Invalid Email Address!"
      Then the response status code should be 422


    Scenario: Extracting user_id
      And user_id should be extracted