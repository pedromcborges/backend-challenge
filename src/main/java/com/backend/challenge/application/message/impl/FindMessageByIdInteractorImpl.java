package com.backend.challenge.application.message.impl;

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

    public FindMessageByIdInteractorImpl(MessageService service) {
        this.service = service;
    }

    @Override
    public MessageResponse execute(UUID uuid) throws NotFoundException {
        Message message = service.findById(uuid);
        return MessageResponse.from(message);
    }
}
