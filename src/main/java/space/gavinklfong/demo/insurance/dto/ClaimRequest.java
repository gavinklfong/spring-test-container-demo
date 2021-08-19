package space.gavinklfong.demo.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimRequest implements Serializable {
    private String id;
    private String customerId;
    private Priority priority;
    private Product product;
    private Double claimAmount;

    public static ClaimRequest generateRandomRequest() {
        return ClaimRequest.builder()
                .id(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .priority(Priority.MEDIUM)
                .build();
    }
}
