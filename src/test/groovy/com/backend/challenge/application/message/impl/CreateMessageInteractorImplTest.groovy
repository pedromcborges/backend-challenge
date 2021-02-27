package com.backend.challenge.application.message.impl

import com.backend.challenge.application.MessageService
import com.backend.challenge.application.message.mappers.MessageMapper
import com.backend.challenge.application.message.request.CreateMessageRequest
import com.backend.challenge.application.message.response.MessageResponse
import com.backend.challenge.domain.ChannelEnum
import com.backend.challenge.domain.repository.MessageRepository
import spock.lang.Specification

import java.time.LocalDateTime

class CreateMessageInteractorImplTest extends Specification{

    private CreateMessageInteractorImpl createMessageInteractorImpl
    private MessageRepository repository = Mock(MessageRepository)
    private MessageMapper mapper = new MessageMapper()

    void setup() {
        this.createMessageInteractorImpl = new CreateMessageInteractorImpl(new MessageService(repository), mapper)
    }

    def "when save message should do it successfully"() {
        given:
        def createMessageRequest = getDummyCreateMessageRequest("message", ChannelEnum.SMS, "destination")
        def message = mapper.createMessageRequestToMessage(createMessageRequest)

        when:
        def result = this.createMessageInteractorImpl.execute(createMessageRequest)

        then:
        1 * this.repository.save(_) >> message

        result instanceof MessageResponse
        result.message == message.message
        result.sendDate == message.sendDate
        result.destination == message.destination
        result.channel == message.channel
        result.status == result.status

        notThrown()
    }


    private static CreateMessageRequest getDummyCreateMessageRequest(String message, ChannelEnum channel, String destination) {
        CreateMessageRequest createMessageRequest = new CreateMessageRequest()
        createMessageRequest.message = message
        createMessageRequest.channel = channel
        createMessageRequest.destination = destination
        createMessageRequest.sendDate = LocalDateTime.now()

        return createMessageRequest
    }
}
