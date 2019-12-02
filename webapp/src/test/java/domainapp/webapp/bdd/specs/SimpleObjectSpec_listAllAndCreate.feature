Feature: List and Create New Simple Objects

  Scenario: Existing simple objects can be listed and new ones created
    Given there are initially 3 simple objects
    When  I create a new simple object
    Then  there are 4 simple objects

    