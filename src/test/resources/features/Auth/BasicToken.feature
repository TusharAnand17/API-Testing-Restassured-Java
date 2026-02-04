Feature: Update Booking with Basic Authentication
  As an API user
  I want to update an existing booking using Basic Auth
  So that I can modify booking details securely

  Background:
    Given valid admin credentials for basic authentication

  Scenario Outline: Update an existing booking successfully
    Given an existing booking with id <bookingId>
    When I update the booking with firstname "<firstname>", lastname "<lastname>", total price <totalprice>, deposit paid "<depositpaid>", checkin "<checkin>", checkout "<checkout>", and additional needs "<additionalneeds>"
    Then the booking should be updated successfully

    Examples:
      | bookingId | firstname | lastname | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | 5         | James     | Brown    | 657        | true        | 2018-01-01  | 2019-01-01  | Breakfast       |
      | 6         | Susan     | Smith    | 200        | false       | 2020-05-10  | 2020-05-20  | Late Checkout   |
