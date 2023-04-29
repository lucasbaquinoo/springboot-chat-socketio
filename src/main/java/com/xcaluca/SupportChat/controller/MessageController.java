package com.xcaluca.SupportChat.controller;

import com.xcaluca.SupportChat.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class MessageController {

  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/message") // /app/message
  @SendTo("/chatroom/public")
  public Message receivePublishMessage(@Payload Message message) {
    return message;
  }

  @MessageMapping("/private-message")
  public Message receivePrivateMessage(@Payload Message message) {

    simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // /user/David/private
    return message;
  }
}
