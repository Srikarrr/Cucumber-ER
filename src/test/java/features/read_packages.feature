Feature: Read Packages
  @Regression
  Scenario: read package values from excel file
    When open the url
    | https://www.vrlgroup.in/track_consignment.aspx |
    And provide the assignment values and update the package value to the excel file
    | 1061646321 |
    | 1061646322 |
    | 1061646323 |