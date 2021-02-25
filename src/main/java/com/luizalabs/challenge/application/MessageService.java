package com.luizalabs.challenge.application;

import com.luizalabs.challenge.domain.Message;
import com.luizalabs.challenge.domain.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Page<Message> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Message create(Message message) {
        return repository.save(message);
    }
}
