package com.luizalabs.challenge.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message")
public class Message {

    @Id
    public UUID id;

    @Column(name = "send_date")
    public LocalDateTime sendDate;

    public String destination;

    public String message;

    @Enumerated(EnumType.STRING)
    public StatusEnum status;

    @Enumerated(EnumType.STRING)
    public ChannelEnum channel;


    public static Message set(LocalDateTime sendDate, String destination, String message, ChannelEnum channel) {
        Message messageEntity = new Message();
        messageEntity.id = UUID.randomUUID();
        messageEntity.sendDate = sendDate;
        messageEntity.destination = destination;
        messageEntity.message = message;
        messageEntity.status = StatusEnum.NOT_SENT;
        messageEntity.channel = channel;

        return messageEntity;
    }
}
