package com.car.valuation.stepdefinations;

import com.registration.base.WebDriverBase;
import com.registration.pageobject.YourDetailsPage;
import com.registration.pageobject.CarSearchPage;
import com.registration.util.ReadFile;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class CheckRegistrationStepDef extends WebDriverBase {

    private List<String> registrationNumbers;
    private CarSearchPage searchpage;
    private YourDetailsPage yourDetailsPage;
    public String tempReg = null;
    private Map<String, List<String>> listMap;

    @Before
    public void setup() {
        WebDriverBase.initialBrowser();
    }

    @Given("Reads the input text file (.*)")
    public void readsTheInputTextFileINPUT_FILE(String inputFileName) throws IOException {
        registrationNumbers = ReadFile.getCarRegistrationNumbers(inputFileName);
    }
    @When("Navigate to website and perform get my car valuation")
    public void navigateToWebsiteAndPerformAndGetMyCarValuation() {
        listMap = new HashMap<String, List<String>>();
        searchpage = new CarSearchPage();
        searchpage.clickAcceptLink();
        yourDetailsPage = new YourDetailsPage();
        for (String carRegistrationNumber : registrationNumbers) {
            List<String> list = new ArrayList<String>();
            yourDetailsPage = searchpage.sendRegistrationNumAndRandomMileage(carRegistrationNumber);
            if(yourDetailsPage.isRegistrationNumberDisplayed())
            {
                list.add(yourDetailsPage.getRegistrationNumber());
                list.add(yourDetailsPage.getMake());
                list.add(yourDetailsPage.getModel());
                list.add(yourDetailsPage.getYear());
                listMap.put(carRegistrationNumber, list);
                searchpage=yourDetailsPage.clickBackButtonToSearchPage();
            } else {
                System.out.println("Car details not found :"+carRegistrationNumber);
                searchpage=yourDetailsPage.clickBackButtonToSearchPage();
            }

        }
    }

    @Then("Compare the details in output text file (.*)")
    public void compareTheDetailsInOutputTextFileOUTPUT_FILE(String outfileName) throws IOException {
        Map<String, List<String>> expectedMap = ReadFile.getExpectedDetails(outfileName);
        System.out.println("Expected Car Details map size :"+expectedMap.size());
        for (Map.Entry<String, List<String>> entry : expectedMap.entrySet()) {
            System.out.println("Expected car details : " + entry.getKey() + ", Value : " + entry.getValue());

        }
        System.out.println("Actual Car Details map size :"+listMap.size());
        for (Map.Entry<String, List<String>> entry : listMap.entrySet()) {
            System.out.println("Actual car details : " + entry.getKey() + ", Value : " + entry.getValue());

        }

        assertEquals("Car Input details not matched with Output car details", expectedMap, listMap);
    }

    @After
    public void tearDown() {
        WebDriverBase.close();
    }

}

