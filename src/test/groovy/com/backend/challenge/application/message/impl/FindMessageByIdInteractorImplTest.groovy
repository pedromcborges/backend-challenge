package com.backend.challenge.application.message.impl

import com.backend.challenge.application.MessageService
import com.backend.challenge.domain.ChannelEnum
import com.backend.challenge.domain.Message
import com.backend.challenge.domain.repository.MessageRepository
import javassist.NotFoundException
import spock.lang.Specification

import java.time.LocalDateTime

class FindMessageByIdInteractorImplTest  extends Specification{

    private FindMessageByIdInteractorImpl findMessageByIdInteractor
    private MessageRepository repository = Mock(MessageRepository)

    void setup() {
        this.findMessageByIdInteractor = new FindMessageByIdInteractorImpl(new MessageService(repository))
    }

    def "when find message by id should do it successfully"() {
        given:
        def message = getDummyMessage("destination", "message")
        def uuid = message.id

        when:
        def result = this.findMessageByIdInteractor.execute(uuid)

        then:
        1 * this.repository.findById(uuid) >> Optional.of(message)

        result.id == message.id
        result.message == message.message
        result.destination == message.destination
        result.channel == message.channel
        result.status == message.status
        result.sendDate == message.sendDate

        notThrown()
    }

    def "when find message by id should throw NotFound"() {
        given:
        def uuid = UUID.randomUUID()

        when:
        this.findMessageByIdInteractor.execute(uuid)

        then:
        1 * this.repository.findById(uuid) >> Optional.empty()

        thrown(NotFoundException)
    }

    private static Message getDummyMessage(String destination, String message) {
        new Message().set(LocalDateTime.now(), destination, message, ChannelEnum.SMS)
    }
}
