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
}
