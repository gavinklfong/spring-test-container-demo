package bdd.insurance.messaging;

import bdd.CucumberTestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.cloud.stream.annotation.StreamListener;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;

import static space.gavinklfong.demo.insurance.messaging.MessageChannels.CLAIM_STATUS_INPUT;

@Slf4j
@TestComponent
public class ClaimStatusEventListener {

    @Autowired
    private CucumberTestContext testContext;

    @StreamListener(CLAIM_STATUS_INPUT)
    public void handleClaimUpdateEvent(ClaimReviewResult claimReviewResult) {
        testContext.setReceivedClaimUpdate(claimReviewResult);
    }

}

