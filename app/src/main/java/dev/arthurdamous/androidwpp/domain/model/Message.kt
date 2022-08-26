package dev.arthurdamous.androidwpp.domain.model

data class Message(
    val id: Int,
    val message: String,
    val hour: String,
    val isSent: Boolean
)