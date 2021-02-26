package com.backend.challenge.application.message;

import com.backend.challenge.application.message.request.UpdateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;
import javassist.NotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface UpdateMessageInteractor {
    MessageResponse execute(UUID uuid, UpdateMessageRequest request) throws NotFoundException;
}
