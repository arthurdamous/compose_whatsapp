package dev.arthurdamous.androidwpp.presentation

import dev.arthurdamous.androidwpp.domain.model.Message

data class MainState(
    val messageText: String = "",
    val isLoading: Boolean = false,
    val isEnabledToSendMessage: Boolean = false,
    val listOfMessages: List<Message> = emptyList()
)
