package com.luizalabs.challenge.application.message;

import com.luizalabs.challenge.application.message.request.CreateMessageRequest;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import com.luizalabs.challenge.domain.Message;

@FunctionalInterface
public interface CreateMessageInteractor {
    MessageResponse execute(CreateMessageRequest request);
}
