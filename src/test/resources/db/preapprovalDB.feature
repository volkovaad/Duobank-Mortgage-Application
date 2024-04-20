@ui_to_db
Feature: Storing Preapproval Details in Database

  Scenario: Storing Preapproval Details in Database

    Given the user fills out the Preapproval Details form
    When the user submits the form
    Then the data should be stored correctly to the following columns in the "tbl_mortagage" table in the database
      | id                   |
      | realtor_status       |
      | realtor_info         |
      | loan_officer_status  |
      | purpose_loan        |
      | est_purchase_price   |
      | down_payment         |
      | down_payment_percent |
      | src_down_payment     |
      | add_fund_available   |

  Scenario: Each mortgage application should have a unique identifier

    Given the user fills out the Preapproval Details form
    When the user submits the form
    Then the database should ensure that each mortgage application is associated with a unique id

 Scenario: The database should ensure that the realtor_info field is not empty
    Given the user fills out the Preapproval Details form
    When the user submits the form
    Then he realtor_info field is not empty and contains the realtor's name.

  Scenario: The database should ensure that the purpose_loan field is not empty
    Given the user fills out the Preapproval Details form
    When the user submits the form
    Then the purpose_loan field is not empty and contains a valid loan purpose given on the dropdown options

  Scenario: Storing Preapproval Details in Database

    Given the user fills out the Preapproval Details form
    When the user submits the form
    Then the data should be mapped correctly to the following columns in the "tbl_mortagage" table in the database
      | id                   |
      | realtor_status       |
      | realtor_info         |
      | loan_officer_status  |
      | purpose_loan        |
      | est_purchase_price   |
      | down_payment         |
      | down_payment_percent |
      | src_down_payment     |
      | add_fund_available   |










