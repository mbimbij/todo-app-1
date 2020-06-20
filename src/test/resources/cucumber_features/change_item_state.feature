Feature: user "user" changes the state of an item of its todolist

  Background:
    Given users
      | user |
    And the todo items
      | id  | name  | owner | state |
      | id1 | item1 | user  | todo  |
      | id2 | item2 | user  | doing |
      | id3 | item3 | user  | done  |

  Scenario: Change item state use case
    When "user" changes the state of item "id2" to "done"
    Then presented items for "user" are
      | name  | state |
      | item1 | todo  |
      | item2 | done  |
      | item3 | done  |

  Scenario: Change item state rest-style
    Given logged in user "user"
    When "user" changes the state of item "id3" to "doing" through rest interface
    Then change state returned value is
      | id  | name  | state |
      | id3 | item3 | doing |
    When user "user" calls listItems rest method
    Then presented items though rest interface are
      | name  | state |
      | item1 | todo  |
      | item2 | doing |
      | item3 | doing |
