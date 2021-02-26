package com.backend.challenge.application.message.request;

import com.backend.challenge.domain.Message;
import com.backend.challenge.domain.ChannelEnum;
import com.backend.challenge.domain.StatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateMessageRequest {

    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    public String destination;

    public String message;

    @NotNull(message = "Field must not be null")
    public ChannelEnum channel;

    @NotNull(message = "Field must not be null")
    public StatusEnum status;

    public Message update(Message message, UpdateMessageRequest updateMessageRequest) {
        message.destination = updateMessageRequest.destination;
        message.message = updateMessageRequest.message;
        message.channel = updateMessageRequest.channel;
        message.status = updateMessageRequest.status;
        return message;
    }
}
