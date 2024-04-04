
@regression
Feature: Using the Personal Information page

  As a potential homebuyer, I want to use the Personal Information page of my bank's mortgage application to provide my personal details and co-borrower's details (if applicable)
  so that I can move forward with the mortgage application process.

  Background:
    Given user is on the Personal Information page of the mortgage application

  Scenario: User selects yes for the co-borrower's checkbox
    When I select yes for the co-borrower question
    Then an additional section for co-borrower's information should be displayed
    And I fill out the borrower's information
    And I fill out the co-borrower's information
    And the checkbox for accepting the privacy policy should be checked
    And I should navigate to the next page


  Scenario: User selects no for the co-borrower's checkbox
    When I select no for the co-borrower question
    Then no additional section for co-borrower's information should be displayed
    And I fill out the borrower's information
    And the checkbox for accepting the privacy policy should be checked
    And I should navigate to the next page



