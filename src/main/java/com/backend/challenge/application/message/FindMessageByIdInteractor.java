package com.backend.challenge.application.message;

import com.backend.challenge.application.message.response.MessageResponse;
import javassist.NotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface FindMessageByIdInteractor {
    MessageResponse execute(UUID uuid) throws NotFoundException;
}
