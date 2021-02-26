package com.backend.challenge.application.message.request;

import com.backend.challenge.domain.Message;
import com.backend.challenge.domain.ChannelEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateMessageRequest {

    @NotNull(message = "Field must not be null")
    public LocalDateTime sendDate;

    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    public String destination;

    public String message;

    @NotNull(message = "Field must not be null")
    public ChannelEnum channel;

    public static Message toMessage(CreateMessageRequest createMessageRequest) {
        return Message.set(
                createMessageRequest.sendDate,
                createMessageRequest.destination,
                createMessageRequest.message,
                createMessageRequest.channel);
    }
}