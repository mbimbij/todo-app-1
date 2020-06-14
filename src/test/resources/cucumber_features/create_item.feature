Feature: user "user1" creates a todo item

  Scenario: Create item
    Given users
      | user |
    And no items for "user"
    When "user" creates the item
      | name    | state |
      | newItem | todo  |
    Then presented items for "user" are
      | name    | state |
      | newItem | todo  |