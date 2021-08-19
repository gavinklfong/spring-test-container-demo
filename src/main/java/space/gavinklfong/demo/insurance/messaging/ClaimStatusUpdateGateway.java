package space.gavinklfong.demo.insurance.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;

@MessagingGateway
public interface ClaimStatusUpdateGateway {

    @Gateway(requestChannel = MessageChannels.CLAIM_STATUS_OUTPUT)
    void submitClaimReviewUpdate(ClaimReviewResult result);

}
