package com.backend.challenge.application.message.impl

import com.backend.challenge.application.MessageService
import com.backend.challenge.application.message.mappers.MessageMapper
import com.backend.challenge.application.message.request.UpdateMessageRequest
import com.backend.challenge.domain.ChannelEnum
import com.backend.challenge.domain.Message
import com.backend.challenge.domain.StatusEnum
import com.backend.challenge.domain.repository.MessageRepository
import javassist.NotFoundException
import spock.lang.Specification

import java.time.LocalDateTime

class UpdateMessageInteractorImplTest extends Specification{

    private UpdateMessageInteractorImpl updateMessageInteractor
    private MessageRepository repository = Mock(MessageRepository)
    private MessageMapper mapper = new MessageMapper()

    void setup() {
        this.updateMessageInteractor = new UpdateMessageInteractorImpl(new MessageService(repository), mapper)
    }

    def "when update message should do it successfully"() {
        given:
        def message = getDummyMessage("message", "destination")
        def updateRequest = getDummyUpdateMessageRequest()
        def uuid = message.id

        when:
        def result = this.updateMessageInteractor.execute(uuid, updateRequest)

        then:
        1 * this.repository.findById(uuid) >> Optional.of(message)
        1 * this.repository.save(_) >> { arguments ->
            def updatedMessage = arguments[0]

            assert updatedMessage instanceof Message
            assert updatedMessage.message == "updated message"
            assert updatedMessage.destination == "updated destination"
            assert updatedMessage.channel == ChannelEnum.PUSH
            assert updatedMessage.status == StatusEnum.SENT

            return updatedMessage
        }

        result != null
        result.message == updateRequest.message
        result.destination == updateRequest.destination
        result.channel == updateRequest.channel
        result.status == updateRequest.status

        notThrown()
    }

    def "when save message should throw NotFound"() {
        given:
        def message = getDummyMessage("message", "destination")
        def updateRequest = getDummyUpdateMessageRequest()
        def uuid = message.id

        when:
        this.updateMessageInteractor.execute(uuid, updateRequest)

        then:
        1 * this.repository.findById(uuid) >> Optional.empty()
        0 * this.repository.save(_) >> { arguments ->
            def updatedMessage = arguments[0]

            assert updatedMessage instanceof Message
            assert updatedMessage.message == "updated message"
            assert updatedMessage.destination == "updated destination"
            assert updatedMessage.channel == ChannelEnum.PUSH
            assert updatedMessage.status == StatusEnum.SENT

            return updatedMessage
        }

        thrown(NotFoundException)
    }


    private static Message getDummyMessage(String destination, String message) {
        new Message().set(LocalDateTime.now(), destination, message, ChannelEnum.SMS)
    }

    private static UpdateMessageRequest getDummyUpdateMessageRequest() {
        UpdateMessageRequest updateMessageRequest =  new UpdateMessageRequest()
        updateMessageRequest.message = "updated message"
        updateMessageRequest.status = StatusEnum.SENT
        updateMessageRequest.channel = ChannelEnum.PUSH
        updateMessageRequest.destination = "updated destination"

        return updateMessageRequest
    }
}
