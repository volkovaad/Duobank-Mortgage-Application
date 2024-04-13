@regression @smoke
Feature: Employment and income details for loan eligibility

  As a user applying for a mortgage loan,
  I want to provide my employment and income details to the bank,
  So that they can evaluate my eligibility for a loan.

  Background: Common steps for all scenarios
      Given the user is on the Employment and Income page

  @unsuccessful @ui_only
  Scenario:   Checkbox
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

    Then The user should be able to move forward

  Scenario:  Required information is valid
    Given The user enters his employment information
      | employerName | Google           |
      | position     | Junior developer |
      | city         | New York         |
      | state        | New York (NY)    |
      | start_date   | 08/13/2009       |
      | end_date     | 09/12/2015       |
    And Employer Name should be required
    And Clear option should be available
    And user is able to pick the correct state from
      | Select One        |
      | Alabama (AL)        |
      | Alaska (AK)         |
      | Arizona (AZ)        |
      | Arkansas (AR)       |
      | California (CA)     |
      | Colorado (CO)       |
      | Connecticut (CT)    |
      | Delaware (DE)       |
      | Florida (FL)        |
      | Georgia (GA)        |
      | Hawaii (HI)         |
      | Idaho (ID)          |
      | Illinois (IL)       |
      | Indiana (IN)        |
      | Iowa (IA)           |
      | Kansas (KS)         |
      | Kentucky (KY)       |
      | Louisiana (LA)      |
      | Maine (ME)          |
      | Maryland (MD)       |
      | Massachusetts (MA)  |
      | Michigan (MI)       |
      | Minnesota (MN)      |
      | Mississippi (MS)    |
      | Missouri (MO)       |
      | Montana (MT)        |
      | Nebraska (NE)       |
      | Nevada (NV)         |
      | New Hampshire (NH)  |
      | New Jersey (NJ)     |
      | New Mexico (NM)     |
      | New York (NY)       |
      | North Carolina (NC) |
      | North Dakota (ND)   |
      | Ohio (OH)           |
      | Oklahoma (OK)       |
      | Oregon (OR)         |
      | Pennsylvania (PA)   |
      | Rhode Island (RI)   |
      | South Carolina (SC) |
      | South Dakota (SD)   |
      | Tennessee (TN)      |
      | Texas (TX)          |
      | Utah (UT)           |
      | Vermont (VT)        |
      | Virginia (VA)       |
      | Washington (WA)     |
      | West Virginia (WV)  |
      | Wisconsin (WI)      |
      | Wyoming (WY)        |

    Then user should be able to add another employer

 Scenario: Adding another employer

   Given The user enters valid  information for employer 1
     | employerName | Google          |
     | position     | SDET            |
     | city         | Pasadena        |
     | state        | California (CA) |
     | start_date   | 08/13/2009      |
     | end_date     | 09/12/2015      |

   Then user should be able to click on add employer button and fill in with valid information
     | employerName | LalaLend          |
     | position     | Junior developer  |
     | city         | Philadelphia      |
     | state        | Pennsylvania (PA) |
     | start_date   | 15/12/2015        |

   And Clear option should be available and warning popup should be displayed
   And user should be able to clear section2
   Then user fill out employer2 again
     | employerName | LalaLend          |
     | position     | Junior developer  |
     | city         | Philadelphia      |
     | state        | Pennsylvania (PA) |
     | start_date   | 15/12/2015        |
   And the user should be able to clear section1
   Then Remove option should be available

   @unsuccessful
  Scenario Outline: Provide invalid Employment Information
    When The user enters invalid "<employerName>", "<position>", "<city>","<start_date>","<end_date>"
    Then the user should see an error message or not being able to continue application
    Examples:

      | employerName | position  | city | start_date | end_date   |
      | $%dcbkljh    | ef9765fc  | 755  | 09/09/2026 | 09/09/2028 |
      | $%dcbkljh    | 98754!!y7 | ?/}  | 09/09/2020 | 09/09/2018 |
      | $%dcbkljh    | #$%&*^$%& | !@3  | 09/15/2020 | 09/09/2021 |
      |              |           |      |            |            |

  Scenario Outline: The user enters their gross monthly income

    Given User enters valid <gross_income>, <overtime>, <bonuses>, <commissions> and <dividends_interest>
    And gross income should be required field
    Then User should have total left

    Examples:
      | gross_income | overtime | bonuses | commissions | dividends_interest |
      | 10567.45     | 367.00   | 100     | 0           | 456.87             |
      | 8567.87      | 500.00   | 500     | 1500        | 543.76             |
      | 3678.00      | 0.0      | 1000    | 3400        | 865.99             |

@unsuccessful
Scenario: Input validation
  Given all fields should accept only numeric input only 12 characters


 Scenario: Provide Additional Gross Monthly Income
   Given The user is able to select  income source from dropdown and able to Add another income
   Then dropdown should contain
     | Select One                        |
     | Alimony/Child Support             |
     | Social Security/Disability Income |
     | Unemployment Benefits             |
     | Interest and Dividends            |
     | VA Compensation                   |
     | Royalty Payments                  |
     | Other Types of Income             |


 Scenario: Navigate Using Next Button
   Given The user filled in all required fields in the previous sections
     | employerName | Sony       |
     | start_date   | 15/12/2015 |
     | gross_income | 6500       |
   When The user click on the next button
   Then The user should navigate to the next section

  Scenario: Navigate using Previous Button
    Given The user is on employment page
    Then he should be able to navigate back

  Scenario Outline: Unable to navigate using Next Button
    Given The user not filled in "<employerName>" or "<start_date>" or "<gross_income>"  in the current section
    When The user click on the next button1
    Then The user should see an error message indicating the required field that needs to be filled in

    Examples:
      | employerName | start_date | gross_income |
      | Sony         |            |              |
      |              | 15/12/2015 |              |
      |              |            | 6500         |
      |              |            |              |
