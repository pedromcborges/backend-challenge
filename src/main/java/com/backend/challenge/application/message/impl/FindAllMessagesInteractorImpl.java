package com.backend.challenge.application.message.impl;

import com.backend.challenge.application.MessageService;
import com.backend.challenge.application.message.FindAllMessagesInteractor;
import com.backend.challenge.application.message.response.MessageResponse;
import com.backend.challenge.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class FindAllMessagesInteractorImpl implements FindAllMessagesInteractor {

    private final MessageService service;

    public FindAllMessagesInteractorImpl(MessageService service) {
        this.service = service;
    }

    @Override
    public Page<MessageResponse> execute(Pageable pageable) {
        return convert(service.findAll(pageable));
    }

    private Page<MessageResponse> convert(Page<Message> page) {
        return page.map(MessageResponse::from);
    }
}
