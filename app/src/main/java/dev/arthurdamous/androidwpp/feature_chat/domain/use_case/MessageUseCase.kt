package dev.arthurdamous.androidwpp.feature_chat.domain.use_case

data class MessageUseCase(
    val sentMessage: SentMessage,
    val getMessages: GetMessages
)
