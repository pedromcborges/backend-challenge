package com.backend.challenge.application.message.impl

import com.backend.challenge.application.MessageService
import com.backend.challenge.domain.ChannelEnum
import com.backend.challenge.domain.Message
import com.backend.challenge.domain.repository.MessageRepository
import javassist.NotFoundException
import spock.lang.Specification

import java.time.LocalDateTime

class DeleteMessageInteractorImplTest  extends Specification{

    private DeleteMessageInteractorImpl deleteMessageInteractorImpl
    private MessageRepository repository = Mock(MessageRepository)

    void setup() {
        this.deleteMessageInteractorImpl = new DeleteMessageInteractorImpl(new MessageService(repository))
    }

    def "when delete message should do it successfully"() {
        given:
        def message = getDummyMessage("destination", "message")
        def uuid = message.id

        when:
        this.deleteMessageInteractorImpl.execute(uuid)

        then:
        1 * this.repository.findById(uuid) >> Optional.of(message)
        1 * this.repository.delete(message)

        notThrown()
    }

    def "when delete message should throw NotFound"() {
        given:
        def uuid = UUID.randomUUID()

        when:
        this.deleteMessageInteractorImpl.execute(uuid)

        then:
        1 * this.repository.findById(uuid) >> Optional.empty()
        0 * this.repository.delete(_)

        thrown(NotFoundException)
    }

    private static Message getDummyMessage(String destination, String message) {
        new Message().set(LocalDateTime.now(), destination, message, ChannelEnum.SMS)
    }
}
