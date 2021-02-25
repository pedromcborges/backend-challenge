package com.luizalabs.challenge.application.message.request;

import com.luizalabs.challenge.application.message.response.MessageResponse;
import com.luizalabs.challenge.domain.Message;
import com.luizalabs.challenge.domain.StatusEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateMessageRequest {

    public LocalDateTime sendDate;
    public String destination;
    public String message;
    public StatusEnum status;

    public static Message of(CreateMessageRequest createMessageRequest) {
        return Message.set(
                createMessageRequest.sendDate,
                createMessageRequest.destination,
                createMessageRequest.message,
                createMessageRequest.status);
    }
}
