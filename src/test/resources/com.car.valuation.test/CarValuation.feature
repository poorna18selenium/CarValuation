@carSearch
Feature: Car valuation details

  Scenario Outline: Reads the input file and verify car valuation details and print car details not doesn't match
    Given Reads the input text file <INPUT_FILE>
    When  Navigate to website and perform get my car valuation
    Then  Compare the details in output text file <OUTPUT_FILE>

    Examples:
      | INPUT_FILE    | OUTPUT_FILE    |
      | car_input.txt | car_output.txt |
