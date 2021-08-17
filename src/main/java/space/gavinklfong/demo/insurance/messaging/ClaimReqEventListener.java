package space.gavinklfong.demo.insurance.messaging;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import space.gavinklfong.demo.insurance.messaging.dto.ClaimRequestMessage;
import space.gavinklfong.demo.insurance.model.ClaimProcess;
import space.gavinklfong.demo.insurance.model.Status;
import space.gavinklfong.demo.insurance.repository.ClaimProcessRepository;

import java.time.format.DateTimeFormatter;
import java.util.Random;

@Slf4j
@Component
public class ClaimReqEventListener {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private static final int PROCESS_TIME_LOWER_BOUND = 500;

    @Autowired
    private ClaimProcessRepository claimProcessRepo;

    @StreamListener(MessageChannels.CLAIM_REQ_EVENT_INPUT)
    public void handleClaimRequestEvent(ClaimRequestMessage claimRequest) {
        log.info("Claim request processing - start - {}", claimRequest.toString());
        long start = System.currentTimeMillis();

        // Simulate claim process
        try {
            Random random = new Random();
            long processingTime = PROCESS_TIME_LOWER_BOUND + random.nextInt(2000);
            Thread.sleep(processingTime);

            Faker faker = new Faker();
            Status status = Status.values()[random.nextInt(Status.values().length)];
            ClaimProcess process = ClaimProcess.builder()
                    .claimId(claimRequest.getClaimId())
                    .customerId(claimRequest.getCustomerId())
                    .requestId(claimRequest.getId())
                    .status(status)
                    .remarks(faker.lorem().sentence())
                    .build();

            process = claimProcessRepo.save(process);

            long end = System.currentTimeMillis();
            log.info("Claim request processing - end - processing time {}, claim = {}, status = {}", (end - start), claimRequest.toString(), process.getStatus());

        } catch (InterruptedException e) {
            long end = System.currentTimeMillis();
            log.error("claim process failed. processing time = {}, claim = {}", (end - start), claimRequest.toString());
            throw new RuntimeException("claim process failed. id = " + claimRequest.getId());
        }
    }

}

