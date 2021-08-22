package bdd.actions;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import space.gavinklfong.demo.insurance.dto.ClaimRequest;

@TestComponent
@RequiredArgsConstructor
public class RabbitMQActions {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchangeName, ClaimRequest claimRequest) {
        rabbitTemplate.convertAndSend(exchangeName, "#", claimRequest);
    }

    public Object receiveMessage(String queueName) {
        return rabbitTemplate.receiveAndConvert(queueName);
    }

}
