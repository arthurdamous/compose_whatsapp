package dev.arthurdamous.androidwpp.domain.use_case

data class MessageUseCase(
    val sentMessage: SentMessage,
    val getMessages: GetMessages
)
