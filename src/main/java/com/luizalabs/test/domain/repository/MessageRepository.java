package com.luizalabs.test.domain.repository;

import com.luizalabs.test.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
