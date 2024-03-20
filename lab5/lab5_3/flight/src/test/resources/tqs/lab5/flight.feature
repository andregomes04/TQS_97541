@flight_sample
Feature: Booking a flight

  Scenario: Booking a trip from one city to another
    When I navigate to "https://blazedemo.com/"
    And I select "San Diego" as the departure city
    And I select "Dublin" as the destination city
    And I click on Find Flights
    And I choose the first flight
    And I fill in passenger details
    And I click on the Purchase Flight button
    Then I should see the confirmation message "Thank you for your purchase today!"