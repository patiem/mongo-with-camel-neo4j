Feature: System allows registering a new user

  Scenario:  Registration single user is performed properly
    Given John who was born in 17-05-1979 and lives in Chicago Ill
    When POST is performed on users
    Then User is added to system registry and returned with MongoID
    And StatusCode is 200