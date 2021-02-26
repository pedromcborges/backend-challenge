package com.backend.challenge.application;

import com.backend.challenge.domain.Message;
import com.backend.challenge.domain.repository.MessageRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message save(Message message) {
        return repository.save(message);
    }

    public Page<Message> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Message findById(UUID uuid) throws NotFoundException {
        return repository.findById(uuid).orElseThrow(() -> new NotFoundException("Message not found: " + uuid));
    }

    public void delete(Message message) {
        repository.delete(message);
    }
}
