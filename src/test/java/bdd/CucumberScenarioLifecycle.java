package bdd;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;

@Slf4j
@RequiredArgsConstructor
public class CucumberScenarioLifecycle {

    @Before
    public void setup() {

        // reset RabbitMQ
        //

        log.info("Setup scenario completed");
    }

}
