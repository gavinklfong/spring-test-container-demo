package space.gavinklfong.demo.insurance.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimReviewResult {
    @Id
    String claimId;
    String customerId;
    Status status;
    String remarks;
}
