package com.backend.challenge.application.message

import com.backend.challenge.api.controller.MessageController
import com.backend.challenge.application.message.mappers.MessageMapper
import com.backend.challenge.application.message.request.CreateMessageRequest
import com.backend.challenge.application.message.request.UpdateMessageRequest
import com.backend.challenge.application.message.response.MessageResponse
import com.backend.challenge.domain.ChannelEnum
import com.backend.challenge.domain.Message
import com.backend.challenge.domain.StatusEnum
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import spock.lang.Specification
import java.time.LocalDateTime

class MessageControllerTest extends Specification{
    private MessageController controller

    private FindAllMessagesInteractor findAllMessagesInteractor = Mock(FindAllMessagesInteractor)
    private FindMessageByIdInteractor findMessageByIdInteractor = Mock(FindMessageByIdInteractor)
    private CreateMessageInteractor createMessageInteractor = Mock(CreateMessageInteractor)
    private UpdateMessageInteractor updateMessageInteractor = Mock(UpdateMessageInteractor)
    private DeleteMessageInteractor deleteMessageInteractor = Mock(DeleteMessageInteractor)

    private MessageMapper mapper = new MessageMapper()

    void setup() {
        this.controller = new MessageController(findAllMessagesInteractor,
                findMessageByIdInteractor,
                createMessageInteractor,
                updateMessageInteractor,
                deleteMessageInteractor)
    }

    def "when find all messages should do it successfully"() {
        given:
        def pageable = Mock(Pageable)

        def messageList = [getDummyMessage()]
        def pagedMessage = new PageImpl(messageList)

        when:
        this.controller.findAll(pageable)

        then:
        1 * this.findAllMessagesInteractor.execute(pageable) >> pagedMessage

        notThrown()
    }

    def "when find  message by Id should do it successfully"() {
        given:
        def uuid = UUID.randomUUID()
        def response = mapper.messageToResponse(getDummyMessage())

        when:
        this.controller.findById(uuid)

        then:
        1 * this.findMessageByIdInteractor.execute(uuid) >> response

        notThrown()
    }

    def "when create message should do it successfully"() {
        given:
        def request = getDummyCreateMessageRequest("message", null, null)
        def message = mapper.createMessageRequestToMessage(request)
        def response = mapper.messageToResponse(message)

        when:
        this.controller.create(request)

        then:
        1 * this.createMessageInteractor.execute(request) >> response

        notThrown()
    }

    def "when update message should do it successfully"() {
        given:
        def request = getDummyUpdateMessageRequest()
        def message = getDummyMessage()
        def uuid = message.id
        def updatedMessage = mapper.updateMessage(message, request)

        def response = mapper.messageToResponse(updatedMessage)

        when:
        this.controller.update(uuid, request)

        then:
        1 * this.updateMessageInteractor.execute(uuid, request) >> response

        notThrown()
    }

    def "when delete message should do it successfully"() {
        given:
        def message = getDummyMessage()
        def uuid = message.id

        when:
        this.controller.delete(uuid)

        then:
        1 * this.deleteMessageInteractor.execute(uuid)

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

    private static UpdateMessageRequest getDummyUpdateMessageRequest() {
        UpdateMessageRequest updateMessageRequest =  new UpdateMessageRequest()
        updateMessageRequest.message = "updated message"
        updateMessageRequest.status = StatusEnum.SENT
        updateMessageRequest.channel = ChannelEnum.PUSH
        updateMessageRequest.destination = "updated destination"

        return updateMessageRequest
    }

    private static Message getDummyMessage() {
        new Message().set(LocalDateTime.now(), "destination", "message", ChannelEnum.SMS)
    }

}
