package space.gavinklfong.demo.insurance.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimRequestMessage {
    private String id;
    private String claimId;
    private String customerId;
    private Priority priority;
}
