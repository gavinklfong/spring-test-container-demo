package space.gavinklfong.demo.insurance.messaging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertTrue;

@Slf4j
@Testcontainers
public class ClaimProcessMessagingIT {

    private static final String RABBIT_MQ_IMAGE_NAME = "rabbitmq:3.8";

    // will be shared between test methods
    @Container
    private static final RabbitMQContainer RABBIT_MQ_CONTAINER_CONTAINER = new RabbitMQContainer(RABBIT_MQ_IMAGE_NAME).withExposedPorts(5672, 15672);;



//    @Container
//    private final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(RABBIT_MQ_IMAGE_NAME).withExposedPorts(5672, 15672);;
//
//    @BeforeEach
//    public void setUp() {
//        String address = rabbitMQContainer.getHost();
//        Integer port = rabbitMQContainer.getFirstMappedPort();
//
//        log.info("RabbitMQ container - address={}, por={}", address, port);
//    }

    @Test
    void givenRabbitMQReady_whenClaimRequestMessageArrive_thenTriggerClaimProcess() {
        assertTrue(RABBIT_MQ_CONTAINER_CONTAINER.isRunning());
//        assertTrue(rabbitMQContainer.isRunning());
    }

}
