package bdd;

import io.cucumber.java.After;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor
public class CucumberScenarioLifecycle {

    @Autowired
    private CucumberTestContext testContext;

    @After
    public void cleanUp() {

        testContext.reset();

        log.info("Test context clean up completed");
    }

}
