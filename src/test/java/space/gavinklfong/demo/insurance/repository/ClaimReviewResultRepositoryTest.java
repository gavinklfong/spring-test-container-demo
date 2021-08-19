package space.gavinklfong.demo.insurance.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;
import space.gavinklfong.demo.insurance.model.Status;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@Slf4j
@Testcontainers
//@ContextConfiguration(initializers = ClaimProcessRepositoryTest.Initializer.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ClaimReviewResultRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ClaimProcessRepository claimProcessRepo;

    @AfterEach
    void cleanUp() {
        this.claimProcessRepo.deleteAll();
    }

    @Test
    void whenSaveRecord_thenRecordCountShouldBeTheSame() {
        claimProcessRepo.save(ClaimReviewResult.builder()
                .claimId(UUID.randomUUID().toString())
                .status(Status.APPROVED)
                .customerId(UUID.randomUUID().toString())
                .build());
        claimProcessRepo.save(ClaimReviewResult.builder()
                .claimId(UUID.randomUUID().toString())
                .status(Status.APPROVED)
                .customerId(UUID.randomUUID().toString())
                .build());

        List<ClaimReviewResult> claims = claimProcessRepo.findAll();

        assertEquals(2, claims.size());
    }

}
