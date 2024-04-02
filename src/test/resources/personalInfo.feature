@regression
Feature: Using the Personal Information page

  As a potential homebuyer, I want to use the Personal Information page of my bank's mortgage application to provide my personal details and co-borrower's details (if applicable)
  so that I can move forward with the mortgage application process.

  Background:
    Given user is on the Personal Information page of the mortgage application

  

#  @smoke
#  Scenario Outline: Providing Personal Information with Co-borrower
#    When I select <coBorrowerOption> for the co-borrower question
#    Then I should see an additional section for co-borrower's information
#    When I fill out the personal information form
#    And I submit the personal information form
#    Then I should be able to navigate to the next page
#    Examples:
#      | coBorrowerOption |
#      | "Yes"            |
#
#  @smoke
#  Scenario: Providing Personal Information without Co-borrower
#
#    When I select "No" for the co-borrower question
#    Then I should not see an additional section for co-borrower's information
#    When I fill out the personal information form
#    And I submit the personal information form
#    Then I should be able to navigate to the next page
#
#  Scenario Outline: Validating Personal Information Fields
#
#    When I enter "<first_name>", "<middle_name>", "<last_name>", "<suffix>", "<email>", "<dob>", "<ssn>", "<marital_status>", "<cell_phone>", and "<home_phone>"
#    Then I should see the "<field_error_message>" for the corresponding field
#    Examples:
#      | first_name | middle_name | last_name | suffix | email                  | dob       | ssn          | marital_status | cell_phone    | home_phone    | field_error_message       |
#      | John       | M           | Doe       | Jr     | invalid_email@         | 01/01/1990| 123-45-6789 | married        | 123-456-7890 | 987-654-3210 | Invalid email format      |
#      |            |             |          |        | john.doe@email.com    | 01/01/1990| 123-45-6789 | married        | 123-456-7890 | 987-654-3210 | First name is required    |
#      | John       |             | Doe       | Jr     | john.doe@email.com    | 01/01/1990| 123-45-6789 | married        | 123-456-7890 | 987-654-3210 | Middle name must be alphabetic |
#      | John       | M           | Doe       | Jr     | john.doe@email.com    | 01/01/1990| 123-45-6789 | invalid        | 123-456-7890 | 987-654-3210 | Invalid marital status    |
#      | John       | M           | Doe       | Jr     | john.doe@email.com    | invalid   | 123-45-6789 | married        | 123-456-7890 | 987-654-3210 | Invalid date format       |
#      | John       | M           | Doe       | Jr     | john.doe@email.com    | 01/01/1990| 123-45-6789 | married        | 123-456-789  | 987-654-3210 | Invalid phone number format |
#      | John       | M           | Doe       | Jr     | john.doe@email.com    | 01/01/1990| 123-45-6789 | married        | 123-456-7890 | 987-654-321  | Invalid phone number format |
#      | John       | M           | Doe       | Jr     | john.doe@email.com    | 01/01/1990| 123-45-678  | married        | 123-456-7890 | 987-654-3210 | Invalid SSN format        |
#      | John       | M           | Doe       | Jr     | john.doe@email.com    | 01/01/1990| 123-45-6789 | single         | 123-456-7890 | 987-654-3210 |                     |