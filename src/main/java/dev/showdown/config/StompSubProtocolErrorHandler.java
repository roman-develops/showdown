package dev.showdown.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Component;

@Component
public class StompSubProtocolErrorHandler extends org.springframework.web.socket.messaging.StompSubProtocolErrorHandler {

    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {
        Throwable exception = ex.getCause();
        if (exception instanceof MessageDeliveryException) {
            exception = exception.getCause();
        }

        if (exception instanceof InvalidBearerTokenException) {
            return handleInvalidBearerTokenException();
        }

        return super.handleClientMessageProcessingError(clientMessage, ex);
    }

    private Message<byte[]> handleInvalidBearerTokenException() {
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
        accessor.setLeaveMutable(true);
        accessor.setMessage("Invalid token!");

        return MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());

    }
}
