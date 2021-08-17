
Feature: Claim Processing
  Process claim request triggered by messages

  Scenario: Process claim request
    Given claim processing is up and running
    When a claim request is put onto a message queue
    Then claim status is saved to database
    And claim status is sent to message queue for communcation
