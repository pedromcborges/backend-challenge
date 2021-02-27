package com.backend.challenge.api.controller;

import com.backend.challenge.application.message.*;
import com.backend.challenge.application.message.request.CreateMessageRequest;
import com.backend.challenge.application.message.request.UpdateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    @ApiOperation(value = "Create Message")
    @ApiImplicitParam(
            name = "request",
            value = "Create Message",
            required = true,
            dataType = "CreateMessageRequest"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MessageResponse create(@Valid @RequestBody CreateMessageRequest request) {
        return createMessageInteractor.execute(request);
    }

    @ApiOperation(value = "Find All Messages")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<MessageResponse> findAll(Pageable pageable) {
        return findAllMessagesInteractor.execute(pageable);
    }

    @ApiOperation(value = "Find Message By Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public MessageResponse findById(@PathVariable UUID uuid) throws NotFoundException {
        return findMessageByIdInteractor.execute(uuid);
    }

    @ApiOperation(value = "Update Message")
    @ApiImplicitParam(
            name = "request",
            value = "Update Message",
            required = true,
            dataType = "UpdateMessageRequest"
    )
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{uuid}")
    public MessageResponse update(@PathVariable UUID uuid,
                                   @Valid @RequestBody UpdateMessageRequest request) throws NotFoundException {
        return updateMessageInteractor.execute(uuid, request);
    }

    @ApiOperation(value = "Create Message")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) throws NotFoundException {
        deleteMessageInteractor.execute(uuid);
    }
}
