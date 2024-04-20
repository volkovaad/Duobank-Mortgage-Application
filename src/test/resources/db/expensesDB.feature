#@ui_to_db
Feature: Storing Expenses Details in Database
  Background: Common steps for all scenarios
    Given the user fills out the Expenses Details form

  Scenario: Storing Expenses Details in Database
    Then the Expenses data should be stored correctly to the following columns in the "tbl_mortagage" table in the database
      | rent_own_status               |
      | monthly_rental_payment        |
      | first_mortagage_total_payment  |

 Scenario: The database should ensure that the rent_own_status field is not empty
    Then the rent_own_status field is not empty and contains rent checkbox.

  Scenario: The database should ensure that the rent_own_status field is not empty
    Then the rent_own_status field is not empty and contains own checkbox.

  Scenario: The database should ensure that the monthly_rental_payment  field is not empty
    Then the monthly_rental_payment field is not empty and contains a payment.

  Scenario: The database should ensure that the first_mortgage_total_payment field is not empty
    Then the first_mortgage_total_payment field is not empty and contains a payment.


  Scenario: Mapping Preapproval Details in Database
    Then the Expenses data should be mapped correctly to the following columns in the "tbl_mortagage" table in the database
      | rent_own_status               |
      | monthly_rental_payment        |
      | first_mortgage_total_payment  |










