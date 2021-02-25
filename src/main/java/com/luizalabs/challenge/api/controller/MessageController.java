package com.luizalabs.challenge.api.controller;

import com.luizalabs.challenge.application.message.CreateMessageInteractor;
import com.luizalabs.challenge.application.message.FindAllMessagesInteractor;
import com.luizalabs.challenge.application.message.FindMessageByIdInteractor;
import com.luizalabs.challenge.application.message.request.CreateMessageRequest;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/messages")
public class MessageController {

    private final FindAllMessagesInteractor findAllMessagesInteractor;
    private final FindMessageByIdInteractor findMessageByIdInteractor;
    private final CreateMessageInteractor createMessageInteractor;

    public MessageController(FindAllMessagesInteractor findAllMessagesInteractor, FindMessageByIdInteractor findMessageByIdInteractor, CreateMessageInteractor createMessageInteractor) {
        this.findAllMessagesInteractor = findAllMessagesInteractor;
        this.findMessageByIdInteractor = findMessageByIdInteractor;
        this.createMessageInteractor = createMessageInteractor;
    }

    @GetMapping
    private Page<MessageResponse> findAll(Pageable pageable) {
        return findAllMessagesInteractor.execute(pageable);
    }

    @GetMapping("/{uuid}")
    private MessageResponse findById(@PathVariable("uuid") UUID uuid) throws NotFoundException {
        return findMessageByIdInteractor.execute(uuid);
    }

    @PostMapping
    private MessageResponse create(@Valid @RequestBody CreateMessageRequest request) {
        return createMessageInteractor.execute(request);
    }
}
