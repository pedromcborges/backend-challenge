package com.backend.challenge.application.message.impl;

import com.backend.challenge.application.message.mappers.MessageMapper;
import com.backend.challenge.application.message.request.UpdateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;
import com.backend.challenge.domain.Message;
import com.backend.challenge.application.MessageService;
import com.backend.challenge.application.message.UpdateMessageInteractor;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateMessageInteractorImpl implements UpdateMessageInteractor {

    private final MessageService service;
    private final MessageMapper mapper;

    public UpdateMessageInteractorImpl(MessageService service, MessageMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public MessageResponse execute(UUID uuid, UpdateMessageRequest request) throws NotFoundException {
        Message message = service.findById(uuid);

        Message updatedMessage = service.save(mapper.updateMessage(message, request));
        return mapper.messageToResponse(updatedMessage);
    }
}
