package com.car.valuation.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "target/test-classes"},
        plugin = {
                "pretty", "html:target/CarValuation-Test-Report",
                "json:target/CarValuation-Test-Report/cucumber.json",
                "rerun:target/CarValuation-Test-Report/rerun.txt"},
        tags = "@carSearch",
        glue = {"com/car/valuation/stepdefinations/"})
public class TestRunner {
}
