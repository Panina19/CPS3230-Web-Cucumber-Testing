Feature: ScanMalta

  Scenario: 1) Valid Login
  Given I am a user on the SCANMalta website
  When I log in using valid credentials using email "aabela007@gmail.com" and password "qwerty123"
  Then I should be logged in

  Scenario: 2) Invalid Login
  Given I am a user on the SCANMalta website
  When I log in using invalid credentials using email "aabela007@gmail.com" and password "qwerty1234"
  Then I should not be logged in

  Scenario: 3) Product Search
  Given I am a logged in user on the SCANMalta website using email "aabela007@gmail.com" and password "qwerty123"
  When I search for a product under "mouse"
  And I select the first product in the list
  Then I should see the product details

  Scenario: 4) Add product to cart
  Given I am a logged in user on the SCANMalta website using email "aabela007@gmail.com" and password "qwerty123"
  And my shopping cart is empty
  When I view the details of a product like "mouse"
  And I choose to buy the product
  Then my shopping cart should contain 1 item

  Scenario Outline: 5) Add multiple products to cart
    Given I am a logged in user on the SCANMalta website using email "aabela007@gmail.com" and password "qwerty123"
    And my shopping cart is empty
    When I add <num-products> products to my shopping cart
    Then my shopping cart should contain <num-products> items
    Examples:
      | num-products |
      | 1            |
      | 2            |
      | 3           |

  Scenario: 6) Removing a product from cart
  Given I am a logged in user on the SCANMalta website using email "aabela007@gmail.com" and password "qwerty123"
  And my shopping cart has 2 products like "mouse" and "keyboard"
  When I remove the first product in my cart
  Then my shopping cart should contain 1 item