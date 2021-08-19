package bdd;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;
import space.gavinklfong.demo.insurance.dto.ClaimRequest;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;

@TestComponent
@RequiredArgsConstructor
@Data
public class CucumberTestContext {
    private ClaimRequest claimRequest;
    private ClaimReviewResult receivedClaimUpdate;

    public void reset() {
        claimRequest = null;
        receivedClaimUpdate = null;
    }
}
