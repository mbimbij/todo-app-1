Feature: user "user" deletes an item from its todolist

  Background:
    Given users
      | user |
    And the todo items
      | id  | name  | owner | state |
      | id1 | item1 | user  | todo  |
      | id2 | item2 | user  | doing |
      | id3 | item3 | user  | done  |

  Scenario: Delete items use case
    When "user" delete the items by id
      | id2 |
      | id3 |
    Then presented items for "user" are
      | name  | state |
      | item1 | todo  |

  Scenario: Delete one item rest-style
    Given logged in user "user"
    When "user" deletes the item "id1" with a rest delete
    And calling listItems rest method
    Then presented items though rest interface are
      | name  | state |
      | item2 | doing |
      | item3 | done  |

  Scenario: Delete multiple items rest controller
    Given logged in user "user"
    When "user" deletes the following items by id through rest controller
      | id1 |
      | id2 |
    And calling listItems rest method
    Then presented items though rest interface are
      | name  | state |
      | item3 | done  |
