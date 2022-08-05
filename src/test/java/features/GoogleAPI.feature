#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Creating, Updating and deleting functionality of Google API

Background: 
Given User establish the server connection  

  Scenario: To validate the Place Id Creation
#    Given User establish the server connection
    When User provides all request details
    And User send the request to the server
    Then User should get the proper server response
    And User should be able to retrieve the created data from database
    And User should be able to match the data

  Scenario: To validate the Place Id Updation
#    Given User establish the server connection
    When User provides all request details for Update Request
    And User send the update request to the server
    Then User should get the proper server response for update
    And User should be able to retrieve the updated data from database
    And User should be able confirm that updated address is available in database
    
  Scenario: To validate the Place Id Deletion
#    Given User establish the server connection
    When User provides all request details for Delete Request
    And User send the delete request to the server
    Then User should get the proper server response for delete
    And User should not be able to retrieve the deleted data from database
    And User should be able confirm that placeid not available in database

    