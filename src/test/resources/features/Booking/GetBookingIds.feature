Feature: Get Booking IDs

  Scenario: Fetch all booking IDs successfully
    When I request all booking IDs
    Then the response status code should be 200
    And the response should contain a list of booking ids
