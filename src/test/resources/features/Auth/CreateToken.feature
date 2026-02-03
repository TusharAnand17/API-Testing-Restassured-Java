Feature: Authentication - Token Generation

  As an API user
  I want to generate an authentication token
  So that I can use secured endpoints

  Scenario: Generate authentication token with valid admin credentials
    Given valid admin credentials for token generation
    When I request an authentication token
    Then a valid token should be generated
