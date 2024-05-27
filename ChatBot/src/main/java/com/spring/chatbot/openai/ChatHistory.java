package com.spring.chatbot.openai;

import com.spring.chatbot.openai.api.Message;
import lombok.Builder;


import java.util.List;

@Builder
public record ChatHistory(List<Message> chatMessages) {
}
