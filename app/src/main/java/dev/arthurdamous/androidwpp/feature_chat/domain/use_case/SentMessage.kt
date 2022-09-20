package dev.arthurdamous.androidwpp.feature_chat.domain.use_case

import android.annotation.SuppressLint
import dev.arthurdamous.androidwpp.feature_chat.domain.model.Message
import dev.arthurdamous.androidwpp.feature_chat.domain.repository.MessageRepository
import dev.arthurdamous.androidwpp.util.SimpleResource
import java.time.LocalDateTime
import java.util.*

class SentMessage(
    private val repository: MessageRepository
) {

    @SuppressLint("NewApi")
    @Throws(Exception::class)
    suspend operator fun invoke(text: String): SimpleResource {
        if (text.isEmpty()) throw Exception("Text can not be null")

        val message = Message(
            id = UUID.randomUUID().toString(),
            message = text,
            isSent = true,
            hour = LocalDateTime.now().toString()
        )
        return repository.sendMessage(message = message)
    }

}
