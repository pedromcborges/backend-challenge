package com.luizalabs.challenge.application.message.request;

import com.luizalabs.challenge.domain.ChannelEnum;
import com.luizalabs.challenge.domain.Message;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateMessageRequest {

    @NotNull(message = "Field send date should not be null")
    public LocalDateTime sendDate;

    @NotNull(message = "Field destination should not be null")
    @NotBlank(message = "Field destination should not be blank")
    public String destination;

    public String message;

    @NotNull(message = "Field destination should not be null")
    public ChannelEnum channel;

    public static Message toMessage(CreateMessageRequest createMessageRequest) {
        return Message.set(
                createMessageRequest.sendDate,
                createMessageRequest.destination,
                createMessageRequest.message,
                createMessageRequest.channel);
    }
}
