package com.backend.challenge.application.message.impl;

import com.backend.challenge.application.MessageService;
import com.backend.challenge.application.message.DeleteMessageInteractor;
import com.backend.challenge.domain.Message;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteMessageInteractorImpl implements DeleteMessageInteractor {

    private final MessageService service;

    public DeleteMessageInteractorImpl(MessageService service) {
        this.service = service;
    }

    @Override
    public void execute(UUID uuid) throws NotFoundException {
        Message message = service.findById(uuid);
        service.delete(message);
    }
}
