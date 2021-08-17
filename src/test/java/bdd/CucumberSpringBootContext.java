package bdd;

import cucumber.runtime.java.spring.GlueScopeConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import space.gavinklfong.demo.insurance.InsuranceApplication;
import static bdd.TestContainersSetup.getRabbitMQContainerIPAddress;
import static bdd.TestContainersSetup.getRabbitMQContainerPort;
import static bdd.TestContainersSetup.getRabbitMQContainerManagementPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        initializers = { CucumberSpringBootContext.initializer.class },
        classes = {InsuranceApplication.class, CucumberTestContext.class, GlueScopeConfiguration.class}
)
@ActiveProfiles(profiles={"bdd"})
@Slf4j
@RequiredArgsConstructor
public class CucumberSpringBootContext {

    public static class initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            TestContainersSetup.initTestContainers(configurableApplicationContext.getEnvironment());

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.rabbitmq.address=" + getRabbitMQContainerIPAddress() + ":" + getRabbitMQContainerPort(),
                    "testcontainers.rabbitmq.management.address=" + getRabbitMQContainerIPAddress() + ":" + getRabbitMQContainerManagementPort()
            );

            values.applyTo(configurableApplicationContext);
        }
    }

    @Before
    public void setup() {
        log.info("Spring Context initialized for BDD tests");
    }
}
