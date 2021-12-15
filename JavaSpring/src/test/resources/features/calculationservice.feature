# language: en
@BDD
Feature: Own and Run Long and Complex Calculations without Blocking
  As a researcher.
  I want a service that can do big number-crunching on the side, and informs me, and only me, when it is ready.
  so that I can continue my work without waiting for the result.

  Background:
    Given at least 1 calculation service is running

  @BDDTUTORIAL
  Scenario Outline: Starting and retrieving a long calculation
    Given user with name A
    And there are 0 more result(s)
    When user A starts an addition of <num1> and <num2>
    And user A downloads the result
    Then 1 result(s) of <result> is downloaded
    And there are 0 more result(s)
    Examples:
      | num1 | num2 | result |
      | 10   | 12   | 22     |
      | 120  | -20  | 100    |

  @BDDTUTORIAL
  Scenario: Can only access results of jobs they initiated
    Given user with name A
    And user with name B
    And there are 0 more result(s)
    When user A starts an addition of 10 and 12
    And user B downloads the result
    Then 0 result(s) of 22 is downloaded
    And there are 1 more result(s)