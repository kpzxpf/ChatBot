package com.spring.chatbot.openai;

import com.spring.chatbot.openai.api.ChatCompletionRequest;

import com.spring.chatbot.openai.api.ChatCompletionResponse;
import com.spring.chatbot.openai.api.Message;
import com.spring.chatbot.openai.api.OpenAIClient;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ChatGptService {

    private final OpenAIClient openAIClient;
    private final ChatGptHistoryService chatGptHistoryService;

    @Nonnull
    public String getResponseChatForUser(Long userId, String userTextInput) {
        chatGptHistoryService.createHistoryIfNotExist(userId);
        var history = chatGptHistoryService.addMessageToHistory(
                userId,
                Message.builder()
                        .content(userTextInput)
                        .role("user")
                        .build()
        );

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4")
                .messages(history.chatMessages())
                .max_tokens(1024)
                .build();
        ChatCompletionResponse response = openAIClient.createChatCompletion(request);

        Message messageFromGpt = response.choices().get(0)
                .message();

        chatGptHistoryService.addMessageToHistory(userId, messageFromGpt);

        return messageFromGpt.content();
    }
}
