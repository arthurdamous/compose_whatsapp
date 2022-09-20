package dev.arthurdamous.androidwpp.feature_chat.presentation

sealed class MessageEvent {
    data class OnMessageSent(val text: String) : MessageEvent()
    data class OnChangeTextMessage(val text: String) : MessageEvent()
    object OnChangeEnabledToSendMessage : MessageEvent()
}
