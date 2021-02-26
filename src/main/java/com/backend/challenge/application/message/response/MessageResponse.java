package com.backend.challenge.application.message.response;

import com.backend.challenge.domain.Message;
import com.backend.challenge.domain.ChannelEnum;
import com.backend.challenge.domain.StatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageResponse {

    public UUID id;
    public LocalDateTime sendDate;
    public String destination;
    public String message;
    public StatusEnum status;
    public ChannelEnum channel;


    public static MessageResponse from(Message message) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.id = message.id;
        messageResponse.sendDate = message.sendDate;
        messageResponse.destination = message.destination;
        messageResponse.message = message.message;
        messageResponse.status = message.status;
        messageResponse.channel = message.channel;

        return messageResponse;
    }
}
