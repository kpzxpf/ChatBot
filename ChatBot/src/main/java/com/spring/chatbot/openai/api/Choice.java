package com.spring.chatbot.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice (@JsonProperty("message") Message message) {}
