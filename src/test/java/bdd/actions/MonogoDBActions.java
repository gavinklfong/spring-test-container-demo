package bdd.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;
import space.gavinklfong.demo.insurance.repository.ClaimProcessRepository;

import java.util.Optional;

@TestComponent
public class MonogoDBActions {

    @Autowired
    private ClaimProcessRepository claimProcessRepository;

    public Optional<ClaimReviewResult> retrieveClaimProcess(String requestId) {
        return claimProcessRepository.findById(requestId);
    }

}
