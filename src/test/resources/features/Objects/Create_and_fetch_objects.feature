Feature: Object Management API
  This feature covers creating an object, fetching by ID, fetching all objects,
  and validating the response data and status codes.

  Background:
    # This runs before each scenario
    Given a valid object creation payload

  Scenario: Create a new object successfully
    When I create the object
    Then the response status code should be 200
    And the response should contain the object name
    And the response should contain data fields

  Scenario: Fetch an object by ID
    When I create the object
    And I fetch the object by id
    Then the response status code should be 200
    And the response should contain the object name
    And the response should contain data fields