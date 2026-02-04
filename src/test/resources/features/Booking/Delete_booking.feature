Feature: Delete Booking with Basic Authentication
  As an API user
  I want to delete an existing booking using Basic Auth
  So that I can remove booking records securely

  Background:
    Given valid admin credentials for basic authentication

  Scenario Outline: Delete an existing booking successfully
    Given an existing booking with id <bookingId>
    When I delete the booking with id <bookingId>
    Then the booking should be deleted successfully

    Examples:
      | bookingId  |
      | 11         |
      | 99         |

#  Scenario: Delete booking with invalid credentials should fail
#    Given invalid admin credentials for basic authentication
#    And an existing booking with id 5
#    When I attempt to delete the booking with id 5
#    Then the booking deletion should be forbidden
