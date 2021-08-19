package space.gavinklfong.demo.insurance.service;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gavinklfong.demo.insurance.dto.ClaimRequest;
import space.gavinklfong.demo.insurance.dto.Product;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;
import space.gavinklfong.demo.insurance.model.Status;
import space.gavinklfong.demo.insurance.repository.ClaimProcessRepository;

import java.util.Random;

@Slf4j
@Service
public class ClaimProcessService {

    private static final int PROCESS_TIME_LOWER_BOUND = 500;

    @Autowired
    private ClaimProcessRepository claimProcessRepo;

    public ClaimReviewResult processClaimRequest(ClaimRequest claimRequest) {
        log.info("Claim request processing - start - {}", claimRequest.toString());
        long start = System.currentTimeMillis();

        // Simulate claim process
        try {
            Random random = new Random();
            long processingTime = PROCESS_TIME_LOWER_BOUND + random.nextInt(500);
            Thread.sleep(processingTime);

            Faker faker = new Faker();

            Status claimStatus = Status.NEED_FOLLOW_UP;
            if (claimRequest.getProduct() == Product.MEDICAL) {
                if (claimRequest.getClaimAmount() < 5000) {
                    claimStatus = Status.APPROVED;
                } else {
                    claimStatus = Status.DECLINED;
                }
            }

            ClaimReviewResult result = ClaimReviewResult.builder()
                    .customerId(claimRequest.getCustomerId())
                    .claimId(claimRequest.getId())
                    .status(claimStatus)
                    .remarks(faker.lorem().sentence())
                    .build();

            result = claimProcessRepo.save(result);

            long end = System.currentTimeMillis();
            log.info("Claim request processing - end - processing time {}, claim = {}, status = {}", (end - start), claimRequest.toString(), result.getStatus());

            return result;

        } catch (InterruptedException e) {
            long end = System.currentTimeMillis();
            log.error("claim process failed. processing time = {}, claim = {}", (end - start), claimRequest.toString());
            throw new RuntimeException("claim process failed. id = " + claimRequest.getId());
        }

    }
}
