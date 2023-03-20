Feature: Social Media Application

  Scenario: Login Using Invalid Credentials
    Given user Opens Facebook Page and Checks If Facebook Is Present In Title and URL
    When user Logins With Invalid UserName and Password
    Then user Takes ScreenShot Of Error "FBLoginError"
    When user Gets A Step Back and Clicks Create New Acount
    When user Enters Invalid Details and Submits
    Then user Takes ScreenShot Of Error "FBCreateNewAccError"