@regression
Feature: Using the Expenses page

  As a potential homebuyer, I want to use the Expenses page of my bank's mortgage application to provide information about my current living situation and expenses
  so that I can move forward with the mortgage application process.




  Background:
  Given I am on the Expenses page of the mortgage application


  Scenario: User selects "Rent"

    When I select Rent
    Then the Monthly Rental Payment field should be required and must only allow positive numbers
    And I should be able to move forward to the next page only after all required fields have been completed



  Scenario: User selects "Own"

    When I select Own
    Then the Monthly Mortgage Payment field should be required and must only allow positive numbers
    And I should be able to move forward to the next page only after all required fields have been completed



  Scenario: User clicks on Previous button

    When I click on Previous button
    Then I should be navigated to the previous page
