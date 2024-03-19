Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
 
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 1998-05-15
      And another book with the title 'One good book 2', written by 'Anonymous', published in 2002-07-10
    When the customer searches for books published between 1997-05-15 and 2003-07-10
    Then 2 books should have been found
      And Book 1 should have the title 'One good book'
      And Book 2 should have the title 'One good book 2'

  Scenario: Search books by title
    Given a book with the title 'Romance Dawn', written by 'Oda', published in 1998-05-15
      And another book with the title 'Water 7', written by 'Oda', published in 2002-07-10
    When the customer searches for books published with the title "Romance Dawn"
    Then 1 book with that title should have been found
      And Book 1 must have the title 'Romance Dawn'