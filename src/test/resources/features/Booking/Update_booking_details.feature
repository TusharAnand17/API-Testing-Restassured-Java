Feature: Update Booking Details

  Background:
    Given valid admin credentials for token generation
    When I request an authentication token
    Then a valid token should be generated

  Scenario Outline: Update an existing booking successfully
    Given an existing booking with id <bookingId>
    When I update the booking with firstname "<firstname>", lastname "<lastname>", total price <totalprice>, deposit paid "<depositpaid>", checkin "<checkin>", checkout "<checkout>", and additional needs "<additionalneeds>"
    Then the booking should be updated successfully
    And the booking firstname should be "<firstname>"
    And the booking lastname should be "<lastname>"
    And the total price should be <totalprice>
    And the deposit paid status should be "<depositpaid>"
    And the checkin date should be "<checkin>"
    And the checkout date should be "<checkout>"
    And the additional needs should be "<additionalneeds>"

    Examples:
      | bookingId | firstname | lastname | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | 5         | James     | Brown    | 111        | true        | 2018-01-01  | 2019-01-01  | Breakfast       |
      | 6         | Alice     | Smith    | 500        | false       | 2020-02-01  | 2020-02-05  | Lunch           |
      | 7         | Bob       | Johnson  | 750        | true        | 2021-03-10  | 2021-03-15  | Dinner          |
#      | 4         | Carol     | White    | 1200       | true        | 2022-04-20  | 2022-04-25  | Gym Access      |
