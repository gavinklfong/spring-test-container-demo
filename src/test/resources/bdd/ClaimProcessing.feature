
Feature: Claim Processing
  Process claim request triggered by messages

  Scenario Outline: Process claim request
    Given claim processing is up and running
    When a claim request of '<product>' policy with claim amount <amount> is submitted queue
    And wait for 2 seconds
    Then the claim case is reviewed and saved to database with status '<result>'
    And wait for 1 seconds
    And the claim status is sent to message queue for communication

    Examples:
    | product | amount | result |
    | HOME    | 10000  | NEED_FOLLOW_UP |
    | MEDICAL | 100    | APPROVED      |
    | MEDICAL | 5000   | DECLINED |
