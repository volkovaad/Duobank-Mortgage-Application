@elshan

  Feature: Consent to do business electronically


    As a mortgage loan applicant
    I want to consent to do business electronically
    So that I can receive and sign the necessary disclosures related to my loan application

  Background:
    Given I am on the E-consent page of the mortgage application

    Scenario: The user should be required to fill all fields
      When The user enter their first and last name and email address
      Then User should be able to proceed

