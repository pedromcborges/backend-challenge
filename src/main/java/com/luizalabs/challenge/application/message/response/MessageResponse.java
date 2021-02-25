package com.luizalabs.challenge.application.message.response;

import com.luizalabs.challenge.domain.Message;
import com.luizalabs.challenge.domain.StatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageResponse {

    public UUID id;
    public LocalDateTime sendDate;
    public String destination;
    public String message;
    public StatusEnum status;


    public static MessageResponse from(Message message) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.id = message.id;
        messageResponse.sendDate = message.sendDate;
        messageResponse.destination = message.destination;
        messageResponse.message = message.message;
        messageResponse.status = message.status;

        return messageResponse;
    }
}
