package space.gavinklfong.demo.insurance.messaging;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@EnableBinding(MessageChannels.class)
@Configuration
public class MessagingConfiguration {
}
