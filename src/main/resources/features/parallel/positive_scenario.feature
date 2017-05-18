@parallel
Feature: Sign In

  Scenario Outline: Sign in with valid credentials
    Given I open "http://localhost:8080" url
    And I enter the following values:
      | email    | <_email>    |
      | password | <_password> |
    When I click the button
    And I wait 5 seconds
    Then I should see a dialog with:
      | <alert> |

    Examples: 
      | _email          | _password | alert                                                |
      | email@domaincom | qwerty    | Well done! You've entered a valid login credentials. |
