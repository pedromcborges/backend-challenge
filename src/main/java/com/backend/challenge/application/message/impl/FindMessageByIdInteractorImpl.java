package com.backend.challenge.application.message.impl;

import com.backend.challenge.application.message.mappers.MessageMapper;
import com.backend.challenge.application.message.response.MessageResponse;
import com.backend.challenge.domain.Message;
import com.backend.challenge.application.MessageService;
import com.backend.challenge.application.message.FindMessageByIdInteractor;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FindMessageByIdInteractorImpl implements FindMessageByIdInteractor {

    private final MessageService service;
    private final MessageMapper mapper;

    public FindMessageByIdInteractorImpl(MessageService service, MessageMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public MessageResponse execute(UUID uuid) throws NotFoundException {
        Message message = service.findById(uuid);
        return mapper.messageToResponse(message);
    }
}
