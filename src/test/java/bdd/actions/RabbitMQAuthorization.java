package bdd.actions;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;

import java.util.Base64;

@TestComponent
public class RabbitMQAuthorization {
    @Getter
    private String authorizationHeader;

    public RabbitMQAuthorization (
            @Value("${spring.rabbitmq.username}") String username,
            @Value("${spring.rabbitmq.password}") String password) {

        String basicAuth = username + ":" + password;
        authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(basicAuth.getBytes());
    }

}
