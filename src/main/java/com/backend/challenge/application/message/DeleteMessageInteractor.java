package com.backend.challenge.application.message;

import javassist.NotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface DeleteMessageInteractor {
    void execute(UUID uuid) throws NotFoundException;
}
