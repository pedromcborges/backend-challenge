package com.backend.challenge.application.message.impl

import com.backend.challenge.application.MessageService
import com.backend.challenge.application.message.mappers.MessageMapper
import com.backend.challenge.domain.ChannelEnum
import com.backend.challenge.domain.Message
import com.backend.challenge.domain.repository.MessageRepository
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import spock.lang.Specification

import java.time.LocalDateTime

class FindAllMessagesInteractorImplTest extends Specification{

    private FindAllMessagesInteractorImpl findAllMessagesInteractor
    private MessageRepository repository = Mock(MessageRepository)
    private MessageMapper mapper = new MessageMapper()

    void setup() {
        this.findAllMessagesInteractor = new FindAllMessagesInteractorImpl(new MessageService(repository), mapper)
    }

    def "when find all messages should do it successfully"() {
        given:
        def pageable = Mock(Pageable)
        def messageList = [getDummyMessage("destination", "message")]
        def pagedMessage = new PageImpl(messageList)

        when:
        def result = this.findAllMessagesInteractor.execute(pageable)

        then:
        1 * this.repository.findAll(pageable) >> pagedMessage

        result.content[0].id == messageList[0].id
        result.content[0].message == messageList[0].message
        result.content[0].destination == messageList[0].destination
        result.content[0].channel == messageList[0].channel
        result.content[0].status == messageList[0].status
        result.content[0].sendDate == messageList[0].sendDate

        notThrown()
    }

    private static Message getDummyMessage(String destination, String message) {
        new Message().set(LocalDateTime.now(), destination, message, ChannelEnum.SMS)
    }
}
