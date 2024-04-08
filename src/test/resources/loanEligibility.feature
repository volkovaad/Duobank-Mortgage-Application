@regression
Feature: Employment and income details for loan eligibility

  As a user applying for a mortgage loan,
  I want to provide my employment and income details to the bank,
  So that they can evaluate my eligibility for a loan.

  Background: Common steps for all scenarios
      Given the user is on the Employment and Income page

  Scenario:   checkbox
    Given The checkbox This is my current job should be unchecked by default

  Scenario: Provide valid Employment and Income Information

    Given The user enters valid employment information
      | employerName | Google          |
      | position     | SDET            |
      | city         | Pasadena        |
      | state        | California (CA) |
      | start_date   | 08/13/2009      |
      | end_date     | 09/12/2015      |

    And  The user enters gross monthly income as
      | gross_income       | 15000 |
      | overtime           | 2500  |
      | bonuses            | 2000  |
      | commissions        | 0     |
      | dividends_interest | 1000  |

    Then Borrower Total Monthly Income field should be automatically calculated

#    When I leave the checkbox for "This is my current job" unchecked
#    And I click on the "Clear" option
#    Then a warning pop-up should appear
#    When I confirm to clear the information
#    Then all information in the section should be cleared

#  Scenario: Add Another Employer Section
#    Given I have entered information for one employer
#    When I click on the "Add another employer" button
#    Then a new section for another employer's information should appear
#    And it should contain the same fields as the first section
#    When I click on the "Clear" option in the new section
#    Then a warning pop-up should appear
#    When I confirm to clear the information
#    Then all information in the new section should be cleared
#    When I click on the "Remove" option in the new section
#    Then the new section should be removed
#

#    When I click on the "Clear" option
#    Then a warning pop-up should appear
#    When I confirm to clear the information
#    Then all information in the section should be cleared
#
#  Scenario: Provide Additional Gross Monthly Income
#    Given I select my income source as "<income_source>"
#    And I enter the amount as "<amount>"
#    When I click on the "Add another income" button
#    Then another set of dropdowns and amount fields should appear
#    And I should be able to enter information for multiple additional income sources
#
#  Scenario Outline: Navigate Using Previous and Next Buttons
#    Given I have filled in all required fields in the previous sections
#    When I click on the "Next" button
#    Then I should navigate to the next section
#    Given I have not filled in all required fields in the current section
#    When I click on the "Next" button
#    Then I should see an error message indicating the required field that needs to be filled in
#    And I should not navigate to the next section
#
#    Examples:
#      | employer        | position  | city        | state | start_date | end_date   | gross_income | overtime | bonuses | commissions | dividends_interest | income_source                     | amount |
#      | ABC Corporation | Manager   | New York    | NY    | 2022-01-01 | 2023-01-01 | 5000.00      | 100.00   | 200.00  | 300.00      | 100.00             | Alimony/Child Support             | 200.00 |
#      | XYZ Industries  | Developer | Los Angeles | CA    | 2021-01-01 | 2022-01-01 | 6000.00      | 150.00   | 250.00  | 350.00      | 150.00             | Social Security/Disability Income | 300.00 |
#
