package com.luizalabs.challenge.application.message.impl;

import com.luizalabs.challenge.application.MessageService;
import com.luizalabs.challenge.application.message.CreateMessageInteractor;
import com.luizalabs.challenge.application.message.request.CreateMessageRequest;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import com.luizalabs.challenge.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class CreateMessageInteractorImpl implements CreateMessageInteractor {

    private final MessageService service;

    public CreateMessageInteractorImpl(MessageService service) {
        this.service = service;
    }

    @Override
    public MessageResponse execute(CreateMessageRequest message) {
        Message createdMessage = service.create(CreateMessageRequest.toMessage(message));
        return MessageResponse.from(createdMessage);
    }
}
