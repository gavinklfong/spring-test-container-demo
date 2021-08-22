package space.gavinklfong.demo.insurance.service;

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

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@SpringJUnitConfig
@ContextConfiguration(classes = {ClaimReviewService.class})
@Tag("UnitTest")
public class ClaimReviewServiceTest {

    @MockBean
    private ClaimProcessRepository claimProcessRepository;

    @Autowired
    private ClaimReviewService claimReviewService;

    @Test
    void givenMedicalClaimWithSmallAmount_whenRunClaimProcess_thenReturnApprovedStatus() {
        when(claimProcessRepository.save(any(ClaimReviewResult.class))).thenAnswer(invocation -> {
            return (ClaimReviewResult) invocation.getArgument(0);
        });

        ClaimRequest request = ClaimRequest.builder()
                .id(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .priority(Priority.MEDIUM)
                .claimAmount(100d)
                .product(Product.MEDICAL)
                .build();

        ClaimReviewResult result = claimReviewService.processClaimRequest(request);

        verify(claimProcessRepository, times(1)).save((any(ClaimReviewResult.class)));

        assertEquals(request.getId(), result.getClaimId());
        assertEquals(request.getCustomerId(), result.getCustomerId());
        assertEquals(Status.APPROVED, result.getStatus());
    }

    @Test
    void givenMedicalClaimWithLargeAmount_whenRunClaimProcess_thenReturnDeclinedStatus() {
        when(claimProcessRepository.save(any(ClaimReviewResult.class))).thenAnswer(invocation -> {
            return (ClaimReviewResult) invocation.getArgument(0);
        });

        ClaimRequest request = ClaimRequest.builder()
                .id(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .priority(Priority.MEDIUM)
                .claimAmount(5000d)
                .product(Product.MEDICAL)
                .build();

        ClaimReviewResult result = claimReviewService.processClaimRequest(request);

        verify(claimProcessRepository, times(1)).save((any(ClaimReviewResult.class)));

        assertEquals(request.getId(), result.getClaimId());
        assertEquals(request.getCustomerId(), result.getCustomerId());
        assertEquals(Status.DECLINED, result.getStatus());
    }

    @Test
    void givenNonMedicalClaim_whenRunClaimProcess_thenReturnNeedFollowUpStatus() {
        when(claimProcessRepository.save(any(ClaimReviewResult.class))).thenAnswer(invocation -> {
            return (ClaimReviewResult) invocation.getArgument(0);
        });

        ClaimRequest request = ClaimRequest.builder()
                .id(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .priority(Priority.MEDIUM)
                .claimAmount(100d)
                .product(Product.HOME)
                .build();

        ClaimReviewResult result = claimReviewService.processClaimRequest(request);

        verify(claimProcessRepository, times(1)).save((any(ClaimReviewResult.class)));

        assertEquals(request.getId(), result.getClaimId());
        assertEquals(request.getCustomerId(), result.getCustomerId());
        assertEquals(Status.NEED_FOLLOW_UP, result.getStatus());
    }
}
