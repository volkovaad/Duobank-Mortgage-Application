@regression @ui_only
Feature: Sign in to my mortgage account

  As a user, I want to be able to sign in to my mortgage account,
  so that I can access my account information and perform necessary actions.

  Background: Common steps for all scenarios
    Given the user is on the log in page

  Scenario:  on login page
    And the user should see a welcome message


  Scenario: User signs in with valid credentials

    When The user fills up the fields with the following info
      | email      | duothechtest@gmail.com |
      | password   | 696XR3dfTbf9W          |
    And password should be masked and required
    Then the user should be redirected to the mortgage account dashboard

  @unsuccessful
  Scenario: User signs in with invalid credentials

    When The user fills up the fields with the  info as
      | email      | duothechlalala@gmail.com |
      | password   | 696XR3dfTbf9W          |

    Then the user should see an error message "Login Failed"



  @unsuccessful
  Scenario: Unsuccessful login wih no username and password

    When the user enters no username and password
    Then the user should not be directed to the personal dashboard


  Scenario: User navigates to sign up page
    Then The user should see a "Don't have an account?"  "Sign up" link
    When The user click the link
    Then The user should be redirected to the sign up page

