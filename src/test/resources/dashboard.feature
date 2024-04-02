@regression
Feature: Access the dashboard page

  As a user who has applied for a mortgage with a bank, I want to be able to access the dashboard page of the bank's mortgage application system
  so that I can view my account information, apply for a new mortgage, and view a list of my previous mortgage applications.



  Scenario: Viewing the dashboard page


    Given the user is logged in
    When the user navigates to the dashboard page
    Then the bank's logo should be displayed in the top left corner

  Scenario: Accessing Mortgage Application page

    When the user clicks on the Mortgage Application link
    Then the user should be taken to a new page for applying for a new mortgage

  Scenario: Viewing account information

    When the user navigates back to the dashboard page
    Then the user's account information should be displayed in the top right corner
    And clicking on the user's name or picture should display a dropdown menu