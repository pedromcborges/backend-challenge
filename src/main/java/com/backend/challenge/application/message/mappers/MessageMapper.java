package com.backend.challenge.application.message.mappers;

import com.backend.challenge.application.message.request.CreateMessageRequest;
import com.backend.challenge.application.message.request.UpdateMessageRequest;
import com.backend.challenge.application.message.response.MessageResponse;
import com.backend.challenge.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public Message createMessageRequestToMessage(CreateMessageRequest createMessageRequest) {
        return Message.set(
                createMessageRequest.sendDate,
                createMessageRequest.destination,
                createMessageRequest.message,
                createMessageRequest.channel);
    }

    public Message updateMessage(Message message, UpdateMessageRequest updateMessageRequest) {
        message.destination = updateMessageRequest.destination;
        message.message = updateMessageRequest.message;
        message.channel = updateMessageRequest.channel;
        message.status = updateMessageRequest.status;
        return message;
    }

    public MessageResponse messageToResponse(Message message) {
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
