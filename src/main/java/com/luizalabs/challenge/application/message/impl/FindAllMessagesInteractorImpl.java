package com.luizalabs.challenge.application.message.impl;

import com.luizalabs.challenge.application.MessageService;
import com.luizalabs.challenge.application.message.FindAllMessagesInteractor;
import com.luizalabs.challenge.application.message.response.MessageResponse;
import com.luizalabs.challenge.domain.Message;
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
