@elshan @smoke @ui_only

  Feature: Consent to do business electronically


    As a mortgage loan applicant
    I want to consent to do business electronically
    So that I can receive and sign the necessary disclosures related to my loan application

  Background:
    Given I am on the E-consent page of the mortgage application

    Scenario: The user should be required to fill all fields
      When The user enter their first and last name and email address
      Then User should be able to proceed

    Scenario: Invalid Email Address Format
      When I fill in the email address field with an invalid email address "invalidemail@"
      And I submit the form
      Then I should see an error message stating "PLEASE ENTER A VALID EMAIL ADDRESS."

    Scenario: Display Necessary Disclosures
      Then I see the necessary disclosures displayed clearly and prominently

    Scenario: Review Disclosures Before Agreement

      When I see the necessary disclosures displayed clearly and prominently
      Then I should see two radio buttons for "Agree" and "Don't Agree"
      Then I see that "Agree" button selected by default

    Scenario: User selects "Don't Agree" button
      When the user selects the "Don't Agree" button
      Then the user should be redirected back to the main application flow

    Scenario: User selects "Agree" button
      When the user selects the "Agree" button
      Then the user can proceed with application

    Scenario: User cannot submit eConsent agreement without selecting an option

      When the user tries to submit the eConsent agreement without selecting any option
      Then an error message should be displayed




