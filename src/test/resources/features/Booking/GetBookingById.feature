Feature: Get Booking Details

  Scenario Outline: Create booking and verify details using booking id
    Given a valid booking creation payload with firstname "<firstname>", lastname "<lastname>", total price <totalprice>, deposit paid "<depositpaid>", checkin "<checkin>", checkout "<checkout>", and additional needs "<additionalneeds>"
    When I create the booking
    Then the response should contain a booking id

    When I fetch booking details using stored booking id
    Then the booking firstname should be "<firstname>"
    And the booking lastname should be "<lastname>"
    And the total price should be <totalprice>
    And the deposit paid status should be "<depositpaid>"
    And the checkin date should be "<checkin>"
    And the checkout date should be "<checkout>"
    And the additional needs should be "<additionalneeds>"

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin     | checkout    | additionalneeds |
      | Tushar    | Anand    | 10000      | true        | 2026-01-01  | 2026-01-10  | Breakfast       |
      | John      | Doe      | 5000       | false       | 2026-02-01  | 2026-02-05  | Lunch           |
      | Alice     | Smith    | 7500       | true        | 2026-03-10  | 2026-03-15  | Dinner          |
      | Bob       | Brown    | 12000      | true        | 2026-04-20  | 2026-04-25  | Gym Access      |
