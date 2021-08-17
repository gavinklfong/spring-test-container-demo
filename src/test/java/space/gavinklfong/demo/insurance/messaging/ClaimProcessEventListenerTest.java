package space.gavinklfong.demo.insurance.messaging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import space.gavinklfong.demo.insurance.messaging.dto.ClaimRequestMessage;
import space.gavinklfong.demo.insurance.messaging.dto.Priority;
import space.gavinklfong.demo.insurance.model.ClaimProcess;
import space.gavinklfong.demo.insurance.repository.ClaimProcessRepository;

import java.util.UUID;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@SpringJUnitConfig
@ContextConfiguration(classes = {ClaimReqEventListener.class})
@Tag("UnitTest")
public class ClaimProcessEventListenerTest {

    @MockBean
    private ClaimProcessRepository claimProcessRepo;

    @Autowired
    private ClaimReqEventListener claimReqEventListener;

    @Test
    void runClaimProcessTest() {

        when(claimProcessRepo.save((any(ClaimProcess.class)))).thenAnswer(invocation -> {
            ClaimProcess process = (ClaimProcess) invocation.getArgument(0);
            return process;
        });

        ClaimRequestMessage message = ClaimRequestMessage.builder()
                .claimId(UUID.randomUUID().toString())
                .id(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .priority(Priority.HIGH)
                .build();

        claimReqEventListener.handleClaimRequestEvent(message);

        verify(claimProcessRepo, times(1)).save(any(ClaimProcess.class));

    }

}
