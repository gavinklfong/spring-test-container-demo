package space.gavinklfong.demo.insurance.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import space.gavinklfong.demo.insurance.dto.ClaimRequest;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;
import space.gavinklfong.demo.insurance.service.ClaimReviewService;

@Slf4j
@Component
public class ClaimReqEventListener {

    @Autowired
    private ClaimReviewService claimReviewService;

    @Autowired
    private ClaimStatusUpdateGateway claimStatusUpdateGateway;

    @StreamListener(MessageChannels.CLAIM_REQ_EVENT_INPUT)
    public void handleClaimRequestEvent(ClaimRequest claimRequest) {
        ClaimReviewResult result = claimReviewService.processClaimRequest(claimRequest);
        claimStatusUpdateGateway.submitClaimReviewUpdate(result);
    }

}

