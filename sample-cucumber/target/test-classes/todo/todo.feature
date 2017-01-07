Feature: DSP Rest API 

  Scenario: Check status of DSP Rest Api
    When I go to url "/status" with method type "GET"
    Then the response code should be "200" and response should be "<businessResponse><status>UP</status></businessResponse>"


  Scenario: Invoke changeSimState for Sim Swap
    Given I have a payload with "<changeSimState><realMsisdn>56900448152</realMsisdn><iccid>8956032135447893547</iccid><state>Swap</state><oldImsi>130030701442605</oldImsi></changeSimState>"
    When I go to url "/changeSimState" with method type "POST"
    Then the response code should be "200" and response should be "<businessResponse><statusInfo><responseCode>0</responseCode><responseDescription>SUCCESS</responseDescription></statusInfo><faultInfo/></businessResponse>"
    And the value of "REAL_MSISDN" for query "select REAL_MSISDN from DSP_SIM_INVENTORY_DTLS where ICCID = 8956032135447893547" should be "56900448152"
    And the value of "SIM_LOAD_STATUS" for query "select SIM_LOAD_STATUS from DSP_SIM_INVENTORY_DTLS where ICCID = 8956032135447893547" should be "16"
    And the value of "EXTRA_INFO_VAL" for query "select EXTRA_INFO_VAL from DSP_EXTRA_INFO_DTLS where ICCID =8956032135447893547 and EXTRA_INFO_ID = 5" should be "130030701442605"
      
      
    Scenario: Check iccid not found
    Given I have a payload with "<changeSimState><realMsisdn>56900448199</realMsisdn><iccid>8956030162599532196</iccid><state>Swap</state><oldImsi>123456789123499</oldImsi></changeSimState>"
    When I go to url "/changeSimState" with method type "POST"
    Then the response code should be "422" and response should be "<businessResponse><statusInfo><responseCode>4</responseCode><responseDescription>INVENTORY_EXCEPTION</responseDescription></statusInfo><faultInfo><faultDescription>ICCID not found in inventory.</faultDescription><faultCode>ERR_INVENTORY_ERROR</faultCode></faultInfo></businessResponse>"
  