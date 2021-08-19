package space.gavinklfong.demo.insurance.messaging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import space.gavinklfong.demo.insurance.dto.ClaimRequest;
import space.gavinklfong.demo.insurance.dto.Priority;
import space.gavinklfong.demo.insurance.dto.Product;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;
import space.gavinklfong.demo.insurance.model.Status;
import space.gavinklfong.demo.insurance.repository.ClaimProcessRepository;
import space.gavinklfong.demo.insurance.service.ClaimProcessService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@SpringJUnitConfig
@ContextConfiguration(classes = {ClaimReqEventListener.class})
@Tag("UnitTest")
public class ClaimReviewResultEventListenerTest {

    @MockBean
    private ClaimStatusUpdateGateway claimStatusUpdateGateway;

    @MockBean
    private ClaimProcessService claimProcessService;

    @Autowired
    private ClaimReqEventListener claimReqEventListener;

    @Test
    void whenClaimRequestArrived_thenInvokeClaimProcessServiceAndClaimStatusUpdateGataway() {

        when(claimProcessService.processClaimRequest((any(ClaimRequest.class)))).thenAnswer(invocation -> {
            ClaimRequest request = (ClaimRequest) invocation.getArgument(0);
            return ClaimReviewResult.builder()
                    .claimId(request.getId())
                    .customerId(request.getCustomerId())
                    .status(Status.APPROVED)
                    .build();
        });

        ClaimRequest message = ClaimRequest.builder()
                .id(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .product(Product.MEDICAL)
                .claimAmount(100d)
                .priority(Priority.HIGH)
                .build();

        claimReqEventListener.handleClaimRequestEvent(message);

        verify(claimStatusUpdateGateway, times(1)).submitClaimReviewUpdate((any(ClaimReviewResult.class)));
    }

}
