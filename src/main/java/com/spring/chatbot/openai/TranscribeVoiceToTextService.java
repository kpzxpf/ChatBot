package com.spring.chatbot.openai;

import com.spring.chatbot.openai.api.CreateTranscriptionRequest;
import com.spring.chatbot.openai.api.OpenAIClient;
import com.spring.chatbot.openai.api.TranscriptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.File;

@Service
@AllArgsConstructor
public class TranscribeVoiceToTextService {

    private final OpenAIClient openAIClient;

    public String transcribe(File audioFile) {
        TranscriptionResponse response = openAIClient.createTranscription(CreateTranscriptionRequest.builder()
                        .audioFile(audioFile)
                        .model("whisper-1")
                .build());
        return response.text();
    }

}
