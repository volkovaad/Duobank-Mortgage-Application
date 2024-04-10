@regression
Feature: Ordering a Credit Report

  As a user applying for a mortgage loan
  I want to be able to order a credit report through the preapproval inquiry page
  So that the bank can verify my eligibility for the loan

  Background:
    Given a user is on the Preapproval Inquiry page

  Scenario: User chooses to order a credit report

    When the user selects "Yes" to order a credit report
    Then the user should be redirected to a secure third-party website
    And the user will fill the credit report order form


  Scenario: User chooses not to order a credit report

    When the user selects "No" to not order a credit report
    Then the user will click "Next" button

