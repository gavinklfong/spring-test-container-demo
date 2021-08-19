package bdd;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor
public class CucumberScenarioLifecycle {

    @Autowired
    private CucumberTestContext testContext;

    @Before
    public void setup() {

        testContext.reset();

        log.info("Setup scenario completed");
    }

}
