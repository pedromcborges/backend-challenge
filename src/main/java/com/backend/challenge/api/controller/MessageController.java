package com.backend.challenge.api.controller;

import com.backend.challenge.application.message.*;
import com.backend.challenge.application.message.request.CreateMessageRequest;
import com.backend.challenge.application.message.request.UpdateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;
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
    private final UpdateMessageInteractor updateMessageInteractor;
    private final DeleteMessageInteractor deleteMessageInteractor;

    public MessageController(FindAllMessagesInteractor findAllMessagesInteractor,
                             FindMessageByIdInteractor findMessageByIdInteractor,
                             CreateMessageInteractor createMessageInteractor,
                             UpdateMessageInteractor updateMessageInteractor,
                             DeleteMessageInteractor deleteMessageInteractor) {
        this.findAllMessagesInteractor = findAllMessagesInteractor;
        this.findMessageByIdInteractor = findMessageByIdInteractor;
        this.createMessageInteractor = createMessageInteractor;
        this.updateMessageInteractor = updateMessageInteractor;
        this.deleteMessageInteractor = deleteMessageInteractor;
    }

    @PostMapping
    private MessageResponse create(@Valid @RequestBody CreateMessageRequest request) {
        return createMessageInteractor.execute(request);
    }

    @GetMapping
    private Page<MessageResponse> findAll(Pageable pageable) {
        return findAllMessagesInteractor.execute(pageable);
    }

    @GetMapping("/{uuid}")
    private MessageResponse findById(@PathVariable UUID uuid) throws NotFoundException {
        return findMessageByIdInteractor.execute(uuid);
    }

    @PutMapping("/{uuid}")
    private MessageResponse update(@PathVariable UUID uuid,
                                   @Valid @RequestBody UpdateMessageRequest request) throws NotFoundException {
        return updateMessageInteractor.execute(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    private void delete(@PathVariable UUID uuid) throws NotFoundException {
        deleteMessageInteractor.execute(uuid);
    }
}
