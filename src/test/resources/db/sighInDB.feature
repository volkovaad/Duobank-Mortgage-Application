@db_only
Feature: User SignUp and LogIn for Duobank Mortgage Application (Database Operations for Duobank Mortgage Application)

  Scenario: Ensure unique email
    Given a user has an existing account so the database should contain one record with email "duothechtest@gmail.com"
    Then  try to add another user with the same email "duothechtest@gmail.com" into tbl_users

  Scenario: Ensure unique id
    Then a user has an existing account with the unique id

  Scenario: Verify the structure of the tbl_users table
    Then The tbl_users table should contain the following columns:
      | id          |
      | email       |
      | password    |
      | first_name  |
      | last_name   |
      | phone       |
      | image       |
      | type        |
      | created_at  |
      | modified_at |
      | zone_id     |
      | church_id   |
      | country_id  |
      | active      |

  @ui_to_db
  Scenario: Timestamp for user account creation in tbl_users table

    When A new user added into tbl_users
    Then The database should contain a record with a timestamp


  @db_only
  Scenario: Passwords are stored as MD5 hash
    When retrieve user's password from the tbl_users table
    Then the password should be an MD5 hash

  @ui_to_db @data_mapping
  Scenario: Verify Sign Up Information Mapping to tbl_users table
    When A new user signs up
    Then The "tbl_user" table data should be mapped correctly to the following columns:
      | email     |
      | password  |
      | first_name |
      | last_name  |

