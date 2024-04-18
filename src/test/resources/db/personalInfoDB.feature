@ui_only
Feature: Storing Personal Information Details in Database


  Background: Common steps for all scenarios
    Given the user fills out the Personal Information Details form
#    When the user submits the form

  Scenario: Storing Borrower's Details in Database
    Then the borrower's data should be stored correctly to the following columns in the "tbl_mortagage" table in the database
      | b_firstName       |
      | b_middleName      |
      | b_lastName        |
      | b_suffix          |
      | b_email           |
      | b_dob             |
      | b_ssn             |
      | b_marital         |
      | b_cell            |
      | b_home            |


  Scenario: Storing Borrower's and Co-borrower's Details in Database
    And the user apply with a co-borrower
    Then the co-borrower's data should be stored correctly to the following columns in the "tbl_mortagage" table in the database
      | c_firstName       |
      | c_middleName      |
      | c_lastName        |
      | c_suffix          |
      | c_email           |
      | c_dob             |
      | c_ssn             |
      | c_marital         |
      | c_cell            |
      | c_home            |


  Scenario: Each mortgage application should have a unique identifier
    Then the database should ensure that each mortgage application is associated with a unique id

 Scenario: The database should ensure that the b_firstName field is not empty
    Then the b_firstName field is not empty and contains the borrower's first name.

  Scenario: The database should ensure that the b_lastName field is not empty
    Then the b_lastName field is not empty and contains  contains the borrower's last name.

  Scenario: The database should ensure that the b_email field is not empty
    Then the b_email field is not empty and contains the borrower's email.

  Scenario: The database should ensure that the b_dob field is not empty
    Then the b_dob field is not empty and contains the borrower's date of birth.

  Scenario: The database should ensure that the b_ssn field is not empty
    Then the b_ssn field is not empty and contains the borrower's social security.

  Scenario: The database should ensure that the b_marital field is not empty
    Then the b_marital field is not empty and contains the borrower's marital status given on the dropdown options.

  Scenario: The database should ensure that the b_cell field is not empty
    Then the b_cell field is not empty and contains the borrower's cellphone.



  Scenario: The database should ensure that the c_firstName field is not empty
    Then the c_firstName field is not empty and contains the co-borrower's first name.

  Scenario: The database should ensure that the c_lastName field is not empty
    Then the c_lastName field is not empty and contains the co-borrower's last name.

  Scenario: The database should ensure that the c_email field is not empty
    Then the c_email field is not empty and contains the co-borrower's email.

  Scenario: The database should ensure that the c_dob field is not empty
    Then the c_dob field is not empty and contains the co-borrower's date of birth.

  Scenario: The database should ensure that the c_ssn field is not empty
    Then the c_ssn field is not empty and contains the co-borrower's social security.

  Scenario: The database should ensure that the c_marital field is not empty
    Then the c_marital field is not empty and contains the co-borrower's marital status given on the dropdown options.

  Scenario: The database should ensure that the c_cell field is not empty
    Then the c_cell field is not empty and contains the co-borrower's cellphone.


  Scenario: Mapping Borrower's Details in Database
    Then the borrower's data should be mapped correctly to the following columns in the "tbl_mortagage" table in the database
      | apply_co_borrower |
      | b_firstName       |
      | b_middleName      |
      | b_lastName        |
      | b_suffix          |
      | b_email           |
      | b_dob             |
      | b_ssn             |
      | b_marital         |
      | b_cell            |
      | b_home            |


  Scenario: Mapping Borrower's and Co-borrower's Details in Database
    Then the co-borrower's data should be mapped correctly to the following columns in the "tbl_mortagage" table in the database
      | apply_co_borrower |
      | c_firstName       |
      | c_middleName      |
      | c_lastName        |
      | c_suffix          |
      | c_email           |
      | c_dob             |
      | c_ssn             |
      | c_marital         |
      | c_cell            |
      | c_home            |









