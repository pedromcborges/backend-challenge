package com.luizalabs.challenge.application.message.impl;

import com.luizalabs.challenge.application.MessageService;
import com.luizalabs.challenge.application.message.UpdateMessageInteractor;
import com.luizalabs.challenge.application.message.request.UpdateMessageRequest;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import com.luizalabs.challenge.domain.Message;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateMessageInteractorImpl implements UpdateMessageInteractor {

    private final MessageService service;

    public UpdateMessageInteractorImpl(MessageService service) {
        this.service = service;
    }

    @Override
    public MessageResponse execute(UUID uuid, UpdateMessageRequest request) throws NotFoundException {
        Message message = service.findById(uuid);

        Message createdMessage = service.save(new UpdateMessageRequest().update(message, request));
        return MessageResponse.from(createdMessage);
    }
}
