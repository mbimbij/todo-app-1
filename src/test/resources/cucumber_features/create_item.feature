Feature: user "user1" creates a todo item

  Background:
    Given users
      | user |
    And the todo items
      | name  | owner | state |
      | item1 | user  | todo  |
      | item2 | user  | doing |
      | item3 | user  | done  |

  Scenario: Create item use case
    When "user" creates the item
      | name    | state |
      | newItem | todo  |
    Then presented items for "user" are
      | name    | state |
      | item1   | todo  |
      | item2   | doing |
      | item3   | done  |
      | newItem | todo  |

  Scenario: Create item rest controller
    Given logged in user "user"
    When "user" creates the item through rest controller
      | name    | state |
      | newItem | todo  |
    Then response is
      | name    | state |
      | newItem | todo  |