Feature: Schema Validation
  Whether the API responses conform to the defined JSON schema.


  Background:
    # This runs before each scenario
    Given a valid object creation payload

  Scenario: The response should match the JSON schema
    When I create the object
    Then the response status code should be 200
    And the response should match the object schema