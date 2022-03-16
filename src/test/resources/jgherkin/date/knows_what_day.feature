Feature: Knows what day
  In order to do business, we must know what day it is including the following (non-inclusive):
    * Is the date a holiday?
    * Is the date a weekday? (weekends are weekly holidays, but not holidays)

  Scenario: Sunday may be fun day, but it is not a work day
    Given today is <day>
    When asked if today is a weekend
    Then the answer should be <answer>

    Examples:
      | day       | answer  |
      | Saturday  | true    |
      | Sunday    | true    |
      | Monday    | false   |
      | Tuesday   | false   |
      | Wednesday | false   |
      | Thursday  | false   |
      | Friday    | false   |
      | NotADay   | false   |
