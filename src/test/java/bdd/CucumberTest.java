package bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty", "html:target/reports/cucumber/html", "json:target/reports/cucumber/cucumber.json"},
        features = { "classpath:features" },
        glue = {"bdd"}
)
public class CucumberTest {
}
