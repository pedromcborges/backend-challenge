package com.backend.challenge.application.message

import com.backend.challenge.application.MessageService
import com.backend.challenge.domain.ChannelEnum
import com.backend.challenge.domain.Message
import com.backend.challenge.domain.repository.MessageRepository
import javassist.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import spock.lang.Specification

import java.time.LocalDateTime

class MessageServiceTest extends Specification{

    private MessageService service
    private MessageRepository repository = Mock(MessageRepository)

    void setup() {
        this.service = new MessageService(repository)
    }

    def "when save message should do it successfully"() {
        given:
        def message = getDummyMessage()

        when:
        this.service.save(message)

        then:
        1 * this.repository.save(message) >> message

        notThrown()
    }

    def "when find all messages should return message list"() {
        given:
        def pageable = Mock(Pageable)
        def messageList = [getDummyMessage()]
        def pagedMessage = new PageImpl(messageList)

        when:
        this.service.findAll(pageable)

        then:
        1 * this.repository.findAll(pageable) >> pagedMessage

        notThrown()
    }

    def "when find message by id should return message"() {
        given:
        def message = getDummyMessage()
        def uuid =  message.id

        when:
        this.service.findById(uuid)

        then:
        1 * this.repository.findById(uuid) >> Optional.of(message)

        notThrown()
    }

    def "when find message by id should return NotFound"() {
        given:
        def uuid =  UUID.randomUUID()

        when:
        this.service.findById(uuid)

        then:
        1 * this.repository.findById(uuid) >> Optional.empty()

        thrown(NotFoundException)
    }

    def "when delete message should do it successfully"() {
        given:
        def message = getDummyMessage()

        when:
        this.service.delete(message)

        then:
        1 * this.repository.delete(message)

        notThrown()
    }

    private static Message getDummyMessage() {
        new Message().set(LocalDateTime.now(), "destination", "message", ChannelEnum.SMS)
    }
}
