package dev.showdown.config;

import dev.showdown.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws");
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
    }

    /**
     * Configures the inbound channel for the client.
     *
     * This method adds an interceptor to the channel which intercepts incoming messages.
     * If the message is a CONNECT command, it extracts the authorization header,
     * validates the token, and sets the authenticated user in the message header.
     *
     * @param registration The channel registration object.
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if (!StompCommand.CONNECT.equals(accessor.getCommand())) {
                    return message;
                }

                String authorizationHeader = accessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION);
                tokenService.extractTokenFromBearerString(authorizationHeader).ifPresent(token -> {
                    try {
                        accessor.setUser(authenticationManager.authenticate(new BearerTokenAuthenticationToken(token)));
                    } catch (InvalidBearerTokenException e) {
                        System.out.println(e); // TODO Add logger. Return error message
                    } catch (Exception e) {
                        System.out.println(e); // TODO Add logger. Return error message
                    }
                });

                return message;
            }
        });
    }
}