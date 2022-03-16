Feature: Stores Value at Key
  In order to be responsive yet flexible, we use a hashmap-like repository w/ the following:
  * values are generic
  * keys are strings and should be formatted like paths

  Scenario: A Value stored is a Value retrievable
    Given stored uniqueValue in the kvstore at uniqueId
    When getting uniqueId from the kvstore
    Then the value should be uniqueValue

  Scenario: A Value created maybe deleted
    Given stored uniqueValue in the kvstore at uniqueId
    And deleted from the kvstore at uniqueId
    When getting uniqueId from the kvstore
    Then the value should be <null>

  Scenario: A Value created maybe updated
    Given stored uniqueValue in the kvstore at uniqueId
    And stored uniqueValue in the kvstore at uniqueId
    When getting uniqueId from the kvstore
    Then the value should be uniqueValue

  Scenario: Multiple Values maybe stored and their keys listed
    Given stored {a:1,b:2,c:3,d:4} in the kvstore
    When keyListing from the kvstore
    Then the keys <a,b,c,d> should be present in the keyListing

