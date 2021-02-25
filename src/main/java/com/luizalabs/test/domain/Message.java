package com.luizalabs.test.domain;

import org.springframework.data.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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


    public static Message set(LocalDateTime sendDate, String destination, String message) {
        Message messageEntity = new Message();
        messageEntity.id = UUID.randomUUID();
        messageEntity.sendDate = sendDate;
        messageEntity.destination = destination;
        messageEntity.message = message;

        return messageEntity;
    }
}
