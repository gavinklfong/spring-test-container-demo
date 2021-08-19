package bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/reports/cucumber/cucumber.json"},
        features = "classpath:bdd/ClaimProcessing.feature",
        glue = {"bdd"}
)
public class CucumberTest {
}
