package org.example.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Booking/Delete_booking.feature",   // path to feature files
        glue = "org.example.api.steps",             // where step definitions live
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        }
//        monochrome = true
)

public class TestRunner {
}