package com.luizalabs.challenge.application.message.impl;

import com.luizalabs.challenge.application.MessageService;
import com.luizalabs.challenge.application.message.FindMessageByIdInteractor;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import com.luizalabs.challenge.domain.Message;
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
