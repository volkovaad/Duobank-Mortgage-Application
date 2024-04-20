@regression @ui_only
Feature: Sign up for a bank mortgage application

  As a potential customer, I want to sign up for an account on the bank mortgage application
  so that I can start the process of applying for a mortgage.

  Background: Common steps for all scenarios
  Given the user is on the login page
  And the user navigate to the Sign Up page

  Scenario: Sign up with valid information

    When The user fills up the fields with valid info
    Then the user  should see a "Registration Successful" message
    And the user  should be redirected to the Sign In page


  Scenario: User enters an email address that is already in use
    When The user fills up the fields with the following info as
      | firsName | Ann                    |
      | lastName | Johns                  |
      | email    | duothechtest@gmail.com |
      | password | 696XR3dfTbf9W          |

    Then the user should see an error message saying "This email already used"

  @unsuccessful
  Scenario: User enters invalid email address
    When the user enters an invalid email address
      | firsName | Ann           |
      | lastName | Johns         |
      | email    | duothechtest  |
      | password | 696XR3dfTbf9W |
    Then the user shouldn't be redirected to the Sign In page

  @unsuccessful
  Scenario: User enters invalid email address1
    When the user enters an invalid email address without .com
      | firsName | Ann                |
      | lastName | Johns              |
      | email    | duothechtest@gmail |
      | password | 696XR3dfTbf9W      |
    Then the user should see an error message

  Scenario: User enters a weak password

    When the user enters a weak password
      | firsName | Ann                     |
      | lastName | Johns                   |
      | email    | duoth1echtest@gmail.com |
      | password | 696XR3                  |
    Then the user should see an error message for password


  Scenario: User clicks on "Already have an account? Sign in" link
    When the user on the Sign Up page he should see a "Already have an account?" "Sign in" link
    And the user click on the link
    Then the user should be redirected to the Sign In page


  Scenario: User signUp
  @unsuccessful
  Scenario Outline: The user enters invalid email address
    When User enters valid info
      | firsName | Ann           |
      | lastName | Johns         |
      | password | 696XR3dfTbf9W |
    And the user enters invalid "<email>"
    Then the user should see an error
    Examples:
      | email                |
      | duothechtest         |
      | missingatsymbol.com  |
      | missingdomain@.com   |
      | special@ex@mple.com  |
      | invalid@example.abcd |

