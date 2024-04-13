@regression @smoke @ui_only
Feature: Mortgage Loan Application Summary Page
  As a user applying for a mortgage loan,
  I want to be able to review and edit the details of my loan application on a summary page,
  So that I can ensure that all of the information is accurate and up-to-date.

  Background: Common steps for all scenarios
    Given the user is on the Summary page

  Scenario: Review and edit loan application details on summary page
    Then I should see a clear indication that it is a review and edit page for the application
    And each section should have an Edit button

  Scenario: Review and edit Pre-approval Page
    When I click the "Edit" button for the "Pre-approval Inquiry" section
    Then I should be taken back to the Pre-approval Inquiry page

  Scenario: Review and edit Personal Details Page
    When I make changes to the "Personal Details" section
    Then I should be taken back to the Personal Details page

  Scenario: Review and edit Expenses Page
    When I make changes to the "Expenses" section
    Then I should be taken back to the Expenses page

  Scenario: Review and edit Employment And Income Page
    When I make changes to the "Employment And Income" section
    Then I should be taken back to the Employment And Income page

  Scenario: Review and edit Credit Report Page
    When I make changes to the "Credit Report" section
    Then I should be taken back to the Credit Report page

  Scenario: Review and edit EConsent Page
    When I make changes to the "EConsent" section
    Then I should be taken back to the EConsent page

  Scenario: Return to Summary Page
    And I return to the Summary page

  Scenario: Save Application
    And I click the Save button
    Then I should be taken back to the start of the mortgage application page