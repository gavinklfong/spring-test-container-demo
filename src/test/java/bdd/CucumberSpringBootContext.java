package bdd;

import io.cucumber.spring.CucumberContextConfiguration;
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

import static bdd.TestContainersSetup.*;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        initializers = { CucumberSpringBootContext.initializer.class },
        classes = {InsuranceApplication.class, CucumberTestContextConfig.class}
//        classes = InsuranceApplication.class,
//        loader = SpringBootContextLoader.class
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
                    "spring.rabbitmq.host=" + getRabbitMQContainerIPAddress(),
                    "spring.rabbitmq.port=" + getRabbitMQContainerPort(),
                    "test-container.rabbitmq.management.address=" + getRabbitMQContainerIPAddress() + ":" + getRabbitMQContainerManagementPort(),
                    "spring.data.mongodb.uri=" + getMongoDBContainerUri() );

            values.applyTo(configurableApplicationContext);
        }
    }

    @Before
    public void setUp() {
        log.info("Spring Context initialized for BDD tests");
    }
}
