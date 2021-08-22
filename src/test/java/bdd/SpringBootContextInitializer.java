package bdd;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import static bdd.TestContainersSetup.*;

public class SpringBootContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        TestContainersSetup.initTestContainers(configurableApplicationContext.getEnvironment());

        TestPropertyValues values = TestPropertyValues.of(
                "spring.rabbitmq.host=" + getRabbitMQContainerIPAddress(),
                "spring.rabbitmq.port=" + getRabbitMQContainerPort(),
                "test-containers.rabbitmq.management.address=" + getRabbitMQContainerIPAddress() + ":" + getRabbitMQContainerManagementPort(),
                "spring.data.mongodb.uri=" + getMongoDBContainerUri()
        );

        values.applyTo(configurableApplicationContext);
    }
}
