Feature: Testing messages service
 
  Scenario: Check if health service is UP
    Given A url "http://localhost:8081/status"
    When User goes to above URL with method type as "GET"
    Then response should be "UP" 
    And  status should be "200" 
    
    
  Scenario: Check if some service is UP
    Given A url "http://localhost:8081/greeting"
    When User goes to above URL with method type as "GET"
    Then  status should be "201" 