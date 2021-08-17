package bdd.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;

@Slf4j
@TestConfiguration
@RequiredArgsConstructor
public class RabbitMQReset {
//    private final RabbitMQClient rabbitMQClient;

    // get all MQ
    // GetMapping(value = "/queues/${spring.rabbitmq.virtual-host}
    // List<Queue> getAllQueues(@RequestHeader("Authorization") String authorizationHeader)

    // delete messages of each MQ
    // DeleteMapping(value = "/queues/${spring.rabbitmq.virtual-host}/{queue}/contents
    // void deleteMessages(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("queue") String queue)

}
