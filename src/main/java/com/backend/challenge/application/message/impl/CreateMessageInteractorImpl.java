package com.backend.challenge.application.message.impl;

import com.backend.challenge.application.message.CreateMessageInteractor;
import com.backend.challenge.application.message.mappers.MessageMapper;
import com.backend.challenge.domain.Message;
import com.backend.challenge.application.MessageService;
import com.backend.challenge.application.message.request.CreateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateMessageInteractorImpl implements CreateMessageInteractor {

    private final MessageService service;
    private final MessageMapper mapper;

    public CreateMessageInteractorImpl(MessageService service, MessageMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public MessageResponse execute(CreateMessageRequest request) {
        Message createdMessage = service.save(mapper.createMessageRequestToMessage(request));
        return mapper.messageToResponse(createdMessage);
    }
}
