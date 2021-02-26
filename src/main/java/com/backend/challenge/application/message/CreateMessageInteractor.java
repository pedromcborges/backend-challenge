package com.backend.challenge.application.message;

import com.backend.challenge.application.message.request.CreateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;

@FunctionalInterface
public interface CreateMessageInteractor {
    MessageResponse execute(CreateMessageRequest request);
}
