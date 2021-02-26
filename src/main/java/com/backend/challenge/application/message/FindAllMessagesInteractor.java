package com.backend.challenge.application.message;

import com.backend.challenge.application.message.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface FindAllMessagesInteractor {
    Page<MessageResponse> execute(Pageable pageable);
}
