package bdd;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

@Slf4j
public class TestContainersSetup {

    private static final int RABBITMQ_PORT = 5672;
    private static final int RABBITMQ_MANAGEMENT_PORT = 15672;
    private static final String RABBITMQ_IMAGE = "rabbitmq:3.8";
    private static final String RABBITMQ_DEFAULT_VHOST = "RABBITMQ_DEFAULT_VHOST";
    private static final String RABBITMQ_DEFAULT_USER = "RABBITMQ_DEFAULT_USER";
    private static final String RABBITMQ_DEFAULT_PASS = "RABBITMQ_DEFAULT_PASS";

    private static final Logger RABBITMQ_LOGGER = LoggerFactory.getLogger("container.RabbitMQ");

    private static final RabbitMQContainer rabbitMqContainer = new RabbitMQContainer(RABBITMQ_IMAGE)
            .withExposedPorts(RABBITMQ_PORT);

    static void initTestContainers(ConfigurableEnvironment configEnv) {
        rabbitMqContainer.addEnv(RABBITMQ_DEFAULT_VHOST, configEnv.getProperty("spring.rabbitmq.virtual-host"));
        rabbitMqContainer.addEnv(RABBITMQ_DEFAULT_USER, configEnv.getProperty("spring.rabbitmq.username"));
        rabbitMqContainer.addEnv(RABBITMQ_DEFAULT_PASS, configEnv.getProperty("spring.rabbitmq.password"));
        rabbitMqContainer.start();
        rabbitMqContainer.followOutput(new Slf4jLogConsumer(RABBITMQ_LOGGER));
    }

    public static String getRabbitMQContainerIPAddress() {
        return rabbitMqContainer.getContainerIpAddress();
    }

    public static int getRabbitMQContainerPort() {
        return rabbitMqContainer.getMappedPort(RABBITMQ_PORT);
    }

    public static int getRabbitMQContainerManagementPort() {
        return rabbitMqContainer.getMappedPort(RABBITMQ_MANAGEMENT_PORT);
    }

}
