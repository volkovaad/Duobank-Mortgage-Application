@db  @ui_only
Feature: Storing Preapproval Details in Database

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

  Scenario: Each mortgage application should have a unique identifier

    Given the user fills out the Preapproval Details form
    When the user submits the form
    Then the database should ensure that each mortgage application is associated with a unique id
#    And  And the database should ensure that the realtor status field is set to 1 for "Yes" and 2 for "No"





