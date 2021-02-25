package com.luizalabs.challenge.api.controller;

import com.luizalabs.challenge.application.message.FindAllMessagesInteractor;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/messages")
public class MessageController {

    private final FindAllMessagesInteractor findAllMessagesInteractor;

    public MessageController(FindAllMessagesInteractor findAllMessagesInteractor) {
        this.findAllMessagesInteractor = findAllMessagesInteractor;
    }

    @GetMapping
    private Page<MessageResponse> findAllMessages(Pageable pageable) {
        return findAllMessagesInteractor.execute(pageable);
    }
}
