Feature: registerAccount
  # scenario1: All input fields except lastName are given
  # scenario2: All input fields except confirmPassword are correctly given
  # scenario3: All input fields are correctly given except chosen field of termsAndConditions
  # scenario4: All input fields are correctly given and account is created successfully

  Scenario Outline:
    Given User opens "<browser>" to fill out account registration page
    And User enters personal information "<dateOfBirth>", "<firstName>", "<lastName>"
    And User enters email and confirmed email "<emailAdd>", "<confEmail>"
    And User enters password and confirmed password"<password>", "<confPass>"
    And User enters roles "<role>"
    And User specifies terms and conditions "<termsCons>"
    And User is above eighteen "<ageIsChecked>"
    And User checks confirm preferences "<comPrefs>"
    And User checks code of ethics "<cOEthics>"
    When User clicks the button (Confirm And Join)
    Then Account should be registered if "<lastNameIsFilled>", "<confirmPassIsCorrect>", "<termsConsIsChecked>"

    Examples:
      | browser | dateOfBirth | firstName | lastName | emailAdd              | confEmail             | password     | confPass     | role    | termsCons | ageIsChecked | comPrefs | cOEthics | lastNameIsFilled | confirmPassIsCorrect | termsConsIsChecked |
      | Chrome  | 11/1/2000   | Ali       |          | Ali@ali.com           | Ali@ali.com           | ABC1XYZ      | ABC1XYZ      | Welfare | check     | check        | check    | check    | false            | true                 | true               |
      | Firefox | 12/12/1980  | Sally     | Anderson | Sally@andy.com        | Sally@andy.com        | Sally1Andy   | Molly1Andy   | Sports  | check     | check        | check    | check    | true             | false                | true               |
      | Edge    | 21/3/1999   | Martin    | Wolf     | Martin@wolf.com       | Martin@wolf.com       | MartinWolf22 | MartinWolf22 | Coach   | notCheck  | check        | check    | check    | true             | true                 | false              |
      | firefox | 4/10/1977   | HoustonAB | Whitney  | Houston11@whitney.com | Houston11@whitney.com | Houston1234  | Houston1234  | Club    | check     | check        | check    | check    | true             | true                 | true               |