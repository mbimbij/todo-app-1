Feature: Presents todo list to logged in users

  Background:
    Given the todo items
      | name  | owner | state |
      | item1 | user1 | todo  |
      | item2 | user1 | doing |
      | item3 | user1 | done  |
      | item4 | user2 | todo  |
      | item5 | user2 | doing |
      | item6 | user2 | done  |

  Scenario: Present items list from usecase
    Given users
      | user1 |
      | user2 |
    Then presented items for user "user1" are
      | name  | state |
      | item1 | todo  |
      | item2 | doing |
      | item3 | done  |
    Then presented items for user "user2" are
      | name  | state |
      | item4 | todo  |
      | item5 | doing |
      | item6 | done  |

  Scenario: Present items list from rest controller
    Given logged in user "user2"
    When user "user2" calls listItems rest method
    Then presented items though rest interface are
      | name  | state |
      | item4 | todo  |
      | item5 | doing |
      | item6 | done  |