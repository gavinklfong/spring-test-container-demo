package space.gavinklfong.demo.insurance.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageChannels {

    static final String CLAIM_REQ_EVENT_INPUT = "claimReqEventInput";

    static final String CLAIM_STATUS_OUTPUT = "claimStatusEventOutput";
    static final String CLAIM_STATUS_INPUT = "claimStatusEventInput";

    @Input(CLAIM_REQ_EVENT_INPUT)
    SubscribableChannel claimReqInputEventChannel();

    @Output (CLAIM_STATUS_OUTPUT)
    MessageChannel claimStatusOutputEventChannel();

    @Input(CLAIM_STATUS_INPUT)
    SubscribableChannel claimStatusInputEventChannel();

}
