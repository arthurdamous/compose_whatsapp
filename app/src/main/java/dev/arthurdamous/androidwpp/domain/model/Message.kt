package dev.arthurdamous.androidwpp.domain.model

data class Message(
    val id: String,
    val message: String,
    val hour: String,
    val isSent: Boolean
)