package bdd.steps;

import bdd.CucumberTestContext;
import bdd.actions.MonogoDBActions;
import bdd.actions.RabbitMQActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import space.gavinklfong.demo.insurance.dto.ClaimRequest;
import space.gavinklfong.demo.insurance.dto.Priority;
import space.gavinklfong.demo.insurance.dto.Product;
import space.gavinklfong.demo.insurance.model.ClaimReviewResult;
import space.gavinklfong.demo.insurance.model.Status;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClaimProcessSteps {

    @Autowired
    private RabbitMQActions rabbitMQAction;

    @Autowired
    private MonogoDBActions monogoDBActions;

    @Autowired
    private CucumberTestContext testContext;

    @Given("claim processing is up and running")
    public void claim_processing_is_up_and_running() {

    }

    @When("wait for {int} seconds")
    public void waitForXSeconds(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }

    @Then("the claim status is sent to message queue for communication")
    public void claim_status_is_sent_to_message_queue_for_communication() {
        ClaimReviewResult claimUpdate = testContext.getReceivedClaimUpdate();
        ClaimRequest claimRequest = testContext.getClaimRequest();
        assertTrue(claimUpdate.getClaimId().equals(claimRequest.getId()));
    }

    @When("a claim request of {string} policy with claim amount {double} is submitted queue")
    public void a_claim_request_is_submitted_queue(String product, Double amount) {
        ClaimRequest claimRequest = ClaimRequest.builder()
                .id(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .claimAmount(amount)
                .priority(Priority.MEDIUM)
                .product(Product.valueOf(product))
                .build();
        rabbitMQAction.sendMessage("claimSubmitted.exchange", claimRequest);
        testContext.setClaimRequest(claimRequest);
    }

    @Then("the claim case is reviewed and saved to database with status {string}")
    public void claim_status_is_reviewed_and_saved_to_database_with_status(String status) {
        Optional<ClaimReviewResult> result = monogoDBActions.retrieveClaimProcess(testContext.getClaimRequest().getId());
        assertTrue(result.isPresent());
        assertEquals(Status.valueOf(status), result.get().getStatus());
    }
}
