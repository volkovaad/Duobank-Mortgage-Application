@regression
Feature: Using the Applications List page

  As a user viewing the Application List,
  I want to be able to see a list of all my submitted applications
  so that I can keep track of my applications and their status.

  Background: Common steps for all scenarios
    Given the user is on the  Application List page


  Scenario: User views submitted applications
#    When the user selects to show <entriesPerPage> entries per page
#    And the user enters <searchTerm> in the search field
    Then the list of applications should display matching entries
    And each entry should display loan id, borrower name, loan amount, and loan details
#    Examples:
#      | entriesPerPage | searchTerm |
#      | 10             | "Ann"      |
#      | 25             | "N A"      |
#      | 50             |            |
#      | 100            |            |



  Scenario: User views application details
    When the user selects to view details of a specific application
    And the details page should display all information entered for that application







