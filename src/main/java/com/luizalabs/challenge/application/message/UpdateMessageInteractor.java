package com.luizalabs.challenge.application.message;

import com.luizalabs.challenge.application.message.request.UpdateMessageRequest;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import javassist.NotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface UpdateMessageInteractor {
    MessageResponse execute(UUID uuid, UpdateMessageRequest request) throws NotFoundException;
}
