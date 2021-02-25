package com.luizalabs.challenge.application.message;

import com.luizalabs.challenge.application.message.request.CreateMessageRequest;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import javassist.NotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface FindMessageByIdInteractor {
    MessageResponse execute(UUID uuid) throws NotFoundException;
}
