package com.spring.chatbot.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Message (
        @JsonProperty("role") String role,
        @JsonProperty("content") String content) {}
