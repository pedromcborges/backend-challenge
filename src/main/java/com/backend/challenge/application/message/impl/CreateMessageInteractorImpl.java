package com.backend.challenge.application.message.impl;

import com.backend.challenge.application.message.CreateMessageInteractor;
import com.backend.challenge.domain.Message;
import com.backend.challenge.application.MessageService;
import com.backend.challenge.application.message.request.CreateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateMessageInteractorImpl implements CreateMessageInteractor {

    private final MessageService service;

    public CreateMessageInteractorImpl(MessageService service) {
        this.service = service;
    }

    @Override
    public MessageResponse execute(CreateMessageRequest message) {
        Message createdMessage = service.save(CreateMessageRequest.toMessage(message));
        return MessageResponse.from(createdMessage);
    }
}
