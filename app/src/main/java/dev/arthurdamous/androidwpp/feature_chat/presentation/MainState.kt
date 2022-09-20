package dev.arthurdamous.androidwpp.feature_chat.presentation

import dev.arthurdamous.androidwpp.feature_chat.domain.model.Message

data class MainState(
    val messageText: String = "",
    val isLoading: Boolean = false,
    val isEnabledToSendMessage: Boolean = false,
    val listOfMessages: List<Message> = emptyList()
)
