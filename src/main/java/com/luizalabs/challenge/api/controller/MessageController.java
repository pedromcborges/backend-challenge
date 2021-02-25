package com.luizalabs.challenge.api.controller;

import com.luizalabs.challenge.application.message.CreateMessageInteractor;
import com.luizalabs.challenge.application.message.FindAllMessagesInteractor;
import com.luizalabs.challenge.application.message.request.CreateMessageRequest;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/messages")
public class MessageController {

    private final FindAllMessagesInteractor findAllMessagesInteractor;
    private final CreateMessageInteractor createMessageInteractor;

    public MessageController(FindAllMessagesInteractor findAllMessagesInteractor, CreateMessageInteractor createMessageInteractor) {
        this.findAllMessagesInteractor = findAllMessagesInteractor;
        this.createMessageInteractor = createMessageInteractor;
    }

    @GetMapping
    private Page<MessageResponse> findAll(Pageable pageable) {
        return findAllMessagesInteractor.execute(pageable);
    }

    @PostMapping
    private MessageResponse create(@Valid @RequestBody CreateMessageRequest request) {
        return createMessageInteractor.execute(request);
    }
}
